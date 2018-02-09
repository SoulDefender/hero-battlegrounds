package com.epam.microservice.sandbox.microservicesandbox.web.helpers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability;

/**
 * TODO Consider creating implementation based on external properties source
 */
public interface AbilityCheckHelper {

    /**
     * Provides a mapping from ability to ability list
     * which represents relation of superiority
     * @return mapping of ability relations
     */
    Map<Ability, List<Ability>> provideAbilityMapping();

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
