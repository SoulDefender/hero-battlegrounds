package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Environment;
import com.epam.microservice.sandbox.microservicesandbox.model.FightResult;
import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics;
import com.epam.microservice.sandbox.microservicesandbox.model.OpponentHeroes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.epam.microservice.sandbox.microservicesandbox.utils.Utils.scale2;
import static com.epam.microservice.sandbox.microservicesandbox.web.service.AbilityCheckServiceImpl.*;

/**
 * @author Dmytro_Maksutov
 */
@Service
public class BattlegroundServiceImpl implements BattlegroundService {

	private static final String TEN_PERCENT = "0.1";
	private static final BigDecimal FIFTY_PERCENT = new BigDecimal("0.5");

	private final AbilityCheckService abilityChecks;

	public BattlegroundServiceImpl(AbilityCheckService abilityCheckService) {
		this.abilityChecks = abilityCheckService;
	}

	@Override
	public FightResult heroFights(OpponentHeroes opponents) {
		Optional<HeroCharacteristics> firstHeroAbilities = opponents.getFirstHero()
				.getCharacteristics();
		Optional<HeroCharacteristics> secondHeroAbilities = opponents.getSecondHero()
				.getCharacteristics();
		Environment battleGround = opponents.getBattleground();
		return firstHeroAbilities.flatMap(fha -> secondHeroAbilities.map(sha -> {
			BigDecimal firstHeroScore = BigDecimal.ONE;
			BigDecimal secondHeroScore = BigDecimal.ONE;
			firstHeroScore =
					checkBattleGroundPreference(fha, battleGround, firstHeroScore);
			secondHeroScore =
					checkBattleGroundPreference(sha, battleGround, secondHeroScore);

			return Optional.of(play(opponents.getFirstHero(), opponents.getSecondHero(),
					fha, firstHeroScore, sha, secondHeroScore));
		}).orElse(firstHeroWins(opponents, BigDecimal.ONE)))
				.orElseThrow(IllegalArgumentException::new);
	}

	private FightResult play(Hero hero, Hero oponnent, HeroCharacteristics fha, BigDecimal heroScore,
							 HeroCharacteristics sha, BigDecimal oponnentScore) {
		BigDecimal heroCoef = abilityChecks.getAbilityAdvancementCoef(fha.getLeadingAbility(),
				sha.getLeadingAbility());
		BigDecimal heroAttack = scale2(abilityChecks.estimateAttackStrength(fha).multiply(heroCoef));
		BigDecimal heroDefence = scale2(abilityChecks.estimateDefenceStrength(fha).multiply(heroCoef));
		BigDecimal opponentAttack = scale2(abilityChecks.estimateAttackStrength(sha));
		BigDecimal opponentDefence = scale2(abilityChecks.estimateDefenceStrength(sha));

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
		return shortenProbIfNeeded(heroScore.add(FIFTY_PERCENT)).multiply(ONE_HUNDRED).intValue();
	}

	private BigDecimal shortenProbIfNeeded(BigDecimal score) {
		if (score.compareTo(BigDecimal.ONE) > 0) {
			return BigDecimal.ONE;
		}
		return score;
	}

	private Optional<FightResult> firstHeroWins(OpponentHeroes opponents, BigDecimal firstHeroScore) {
		return Optional.of(
				new FightResult(opponents.getFirstHero(), opponents.getSecondHero(),
						convertToPercent(firstHeroScore)));
	}

	private BigDecimal checkBattleGroundPreference(HeroCharacteristics heroAbilities,
												   Environment battleGround,
												   BigDecimal probability) {
		if (!doesHeroPreferTheEnv(heroAbilities, battleGround)) {
			return probability.subtract(new BigDecimal(TEN_PERCENT));
		}
		return probability;
	}

	private boolean doesHeroPreferTheEnv(HeroCharacteristics abilities, Environment battleGround) {
		return abilities.getPreferredEnvironments().contains(battleGround);
	}
}