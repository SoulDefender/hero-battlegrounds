package com.epam.microservice.sandbox.microservicesandbox.web.helpers;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.DEXTERITY;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.INTELLIGENCE;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.MAGIC_POWER;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.MAXIMA;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.STRENGTH;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.WISDOM;

@Component
public class AbilityCheckHelperPlainImpl implements AbilityCheckHelper {

    private Map<Ability, List<Ability>> ABILITY_MAPPING = new HashMap<Ability, List<Ability>>() {
        {
            put(STRENGTH, Arrays.asList(WISDOM, INTELLIGENCE));
            put(DEXTERITY, Arrays.asList(STRENGTH, WISDOM));
            put(WISDOM, Collections.singletonList(INTELLIGENCE));
            put(INTELLIGENCE, Collections.singletonList(DEXTERITY));
            put(MAGIC_POWER, Arrays.asList(DEXTERITY, STRENGTH, INTELLIGENCE, WISDOM));
            put(MAXIMA, Arrays.asList(
                    DEXTERITY, STRENGTH, INTELLIGENCE, WISDOM, MAGIC_POWER
            ));
        }
    };

    private static final BigDecimal STRENGTH_ATTACK_COEF = new BigDecimal("0.25");
    private static final BigDecimal DEXTERITY_ATTACK_COEF = new BigDecimal("0.2");
    private static final BigDecimal MAGIC_ATTACK_COEF = new BigDecimal("0.3");
    private static final BigDecimal INTELLIGENCE_ATTACK_COEF = new BigDecimal("0.15");
    private static final BigDecimal WISDOM_ATTACK_COEF = new BigDecimal("0.1");

    private static final BigDecimal STRENGTH_DEF_COEF = new BigDecimal("0.2");
    private static final BigDecimal DEXTERITY_DEF_COEF = new BigDecimal("0.25");
    private static final BigDecimal MAGIC_DEF_COEF = new BigDecimal("0.2");
    private static final BigDecimal INTELLIGENCE_DEF_COEF = new BigDecimal("0.25");
    private static final BigDecimal WISDOM_DEF_COEF = new BigDecimal("0.1");

    @Override
    public Map<Ability, List<Ability>> provideAbilityMapping() {
        return ABILITY_MAPPING;
    }

    @Override
    public BigDecimal getStrengthAttackCoef() {
        return STRENGTH_ATTACK_COEF;
    }

    @Override
    public BigDecimal getDexterityAttackCoef() {
        return DEXTERITY_ATTACK_COEF;
    }

    @Override
    public BigDecimal getMagicAttackCoef() {
        return MAGIC_ATTACK_COEF;
    }

    @Override
    public BigDecimal getIntelligenceAttackCoef() {
        return INTELLIGENCE_ATTACK_COEF;
    }

    @Override
    public BigDecimal getWisdomAttackCoef() {
        return WISDOM_ATTACK_COEF;
    }

    @Override
    public BigDecimal getStrengthDefCoef() {
        return STRENGTH_DEF_COEF;
    }

    @Override
    public BigDecimal getDexterityDefCoef() {
        return DEXTERITY_DEF_COEF;
    }

    @Override
    public BigDecimal getMagicDefCoef() {
        return MAGIC_DEF_COEF;
    }

    @Override
    public BigDecimal getIntelligenceDefCoef() {
        return INTELLIGENCE_DEF_COEF;
    }

    @Override
    public BigDecimal getWisdomDefCoef() {
        return WISDOM_DEF_COEF;
    }
}
