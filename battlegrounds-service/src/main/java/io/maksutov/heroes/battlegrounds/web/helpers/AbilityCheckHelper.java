package io.maksutov.heroes.battlegrounds.web.helpers;

import io.maksutov.heroes.battlegrounds.model.HeroCharacteristics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * TODO Consider creating implementation based on external properties source
 */
public interface AbilityCheckHelper {

    /**
     * Provides a mapping from ability to ability list
     * which represents relation of superiority
     * @return mapping of ability relations
     */
    Map<HeroCharacteristics.Ability, List<HeroCharacteristics.Ability>> provideAbilityMapping();

    BigDecimal getStrengthAttackCoef();

    BigDecimal getDexterityAttackCoef();

    BigDecimal getMagicAttackCoef();

    BigDecimal getIntelligenceAttackCoef();

    BigDecimal getWisdomAttackCoef();

    BigDecimal getStrengthDefCoef();

    BigDecimal getDexterityDefCoef();

    BigDecimal getMagicDefCoef();

    BigDecimal getIntelligenceDefCoef();

    BigDecimal getWisdomDefCoef();
}
