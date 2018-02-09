package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics;
import com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability;
import com.epam.microservice.sandbox.microservicesandbox.web.helpers.AbilityCheckHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class AbilityCheckServiceImpl implements AbilityCheckService {

    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private final AbilityCheckHelper helper;

    public AbilityCheckServiceImpl(AbilityCheckHelper abilityCheckHelper) {
        this.helper = abilityCheckHelper;
    }

    @Override
    public BigDecimal getAbilityAdvancementCoef(Ability first, Ability second) {
        Map<Ability, List<Ability>> mapping = helper.provideAbilityMapping();
        if (mapping.get(first).contains(second)) {
            return new BigDecimal("1.25");
        } else if (mapping.get(second).contains(first)) {
            return new BigDecimal("0.75");
        } else {
            return BigDecimal.ONE;
        }
    }

    @Override
    public BigDecimal estimateAttackStrength(HeroCharacteristics abils) {
        return abils.getStrength().multiply(helper.getStrengthAttackCoef())
                .add(abils.getDexterity().multiply(helper.getDexterityAttackCoef()))
                .add(abils.getMagicPowerGrantedByUniverse().multiply(helper.getMagicAttackCoef()))
                .add(abils.getIntelligence().multiply(helper.getIntelligenceAttackCoef()))
                .add(abils.getWisdom().multiply(helper.getWisdomAttackCoef()))
                .multiply(BigDecimal.valueOf(Math.log10(
                        abils.getMagicPowerGrantedByUniverse().doubleValue())))
                .divide(ONE_HUNDRED, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal estimateDefenceStrength(HeroCharacteristics abils) {
        return abils.getStrength().multiply(helper.getStrengthDefCoef())
                .add(abils.getDexterity().multiply(helper.getDexterityDefCoef()))
                .add(abils.getMagicPowerGrantedByUniverse().multiply(helper.getMagicDefCoef()))
                .add(abils.getIntelligence().multiply(helper.getIntelligenceDefCoef()))
                .add(abils.getWisdom().multiply(helper.getWisdomDefCoef()))
                .multiply(BigDecimal.valueOf(Math.log10(
                        abils.getMagicPowerGrantedByUniverse().doubleValue())))
                .divide(ONE_HUNDRED, RoundingMode.HALF_DOWN)
                .setScale(2, RoundingMode.HALF_DOWN);
    }

}
