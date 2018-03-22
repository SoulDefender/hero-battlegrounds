package io.maksutov.heroes.battlegrounds.web.service;

import io.maksutov.heroes.battlegrounds.model.Environment;
import io.maksutov.heroes.battlegrounds.model.FightResult;
import io.maksutov.heroes.battlegrounds.model.Hero;
import io.maksutov.heroes.battlegrounds.model.HeroCharacteristics;
import io.maksutov.heroes.battlegrounds.model.OpponentHeroes;
import io.maksutov.heroes.battlegrounds.utils.Constants;
import io.maksutov.heroes.battlegrounds.utils.Utils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Dmytro_Maksutov
 */
@Service
public class BattlegroundServiceImpl implements BattlegroundService {

	private final AbilityCheckService abilityChecks;

	public BattlegroundServiceImpl(AbilityCheckService abilityCheckService) {
		this.abilityChecks = abilityCheckService;
	}

	@Override
	public FightResult heroFights(OpponentHeroes opponents) {
		Optional<HeroCharacteristics> firstHeroAbilities = opponents.getHero()
				.getCharacteristics();
		Optional<HeroCharacteristics> secondHeroAbilities = opponents.getOpponent()
				.getCharacteristics();
		Environment battleGround = opponents.getBattleground();
		return firstHeroAbilities.flatMap(fha -> secondHeroAbilities.map(sha -> {
			BigDecimal firstHeroScore = BigDecimal.ONE;
			BigDecimal secondHeroScore = BigDecimal.ONE;
			firstHeroScore =
					checkBattleGroundPreference(fha, battleGround, firstHeroScore);
			secondHeroScore =
					checkBattleGroundPreference(sha, battleGround, secondHeroScore);

			return Optional.of(play(opponents.getHero(), opponents.getOpponent(),
					fha, firstHeroScore, sha, secondHeroScore));
		}).orElse(firstHeroWins(opponents, BigDecimal.ONE)))
				.orElseThrow(IllegalArgumentException::new);
	}

	private FightResult play(Hero hero, Hero oponnent, HeroCharacteristics fha, BigDecimal heroScore,
							 HeroCharacteristics sha, BigDecimal oponnentScore) {
		BigDecimal heroCoef = abilityChecks.getAbilityAdvancementCoef(fha.getLeadingAbility(),
				sha.getLeadingAbility());
		BigDecimal heroAttack = Utils.scale2(abilityChecks.estimateAttackStrength(fha).multiply(heroCoef));
		BigDecimal heroDefence = Utils.scale2(abilityChecks.estimateDefenceStrength(fha).multiply(heroCoef));
		BigDecimal opponentAttack = Utils.scale2(abilityChecks.estimateAttackStrength(sha));
		BigDecimal opponentDefence = Utils.scale2(abilityChecks.estimateDefenceStrength(sha));

		heroScore = heroDefence.add(heroScore);
		oponnentScore = opponentDefence.add(oponnentScore);

		while (heroScore.compareTo(BigDecimal.ZERO) > 0
				&& oponnentScore.compareTo(BigDecimal.ZERO) > 0) {
			oponnentScore = oponnentScore.subtract(heroAttack);
			heroScore = heroScore.subtract(opponentAttack);
		}

		if (heroScore.compareTo(BigDecimal.ZERO) > 0) {
			return new FightResult(hero, oponnent, convertToPercent(heroScore));
		}
		return new FightResult(oponnent, hero, convertToPercent(oponnentScore));
	}

	private int convertToPercent(BigDecimal heroScore) {

		return shortenProbIfNeeded(heroScore.add(Constants.FIFTY_PERCENT)).multiply(Constants.ONE_HUNDRED).intValue();
	}

	private BigDecimal shortenProbIfNeeded(BigDecimal score) {
		if (score.compareTo(BigDecimal.ONE) > 0) {
			return BigDecimal.ONE;
		}
		return score;
	}

	private Optional<FightResult> firstHeroWins(OpponentHeroes opponents, BigDecimal firstHeroScore) {
		return Optional.of(
				new FightResult(opponents.getHero(), opponents.getOpponent(),
						convertToPercent(firstHeroScore)));
	}

	private BigDecimal checkBattleGroundPreference(HeroCharacteristics heroAbilities,
												   Environment battleGround,
												   BigDecimal probability) {
		if (!doesHeroPreferTheEnv(heroAbilities, battleGround)) {
			return probability.subtract(Constants.TEN_PERCENT);
		}
		return probability;
	}

	private boolean doesHeroPreferTheEnv(HeroCharacteristics abilities, Environment battleGround) {
		return abilities.getPreferredEnvironments().contains(battleGround);
	}
}
