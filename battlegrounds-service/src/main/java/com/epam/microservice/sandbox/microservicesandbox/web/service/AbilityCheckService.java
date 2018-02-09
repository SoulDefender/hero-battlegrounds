package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics;

import java.math.BigDecimal;

public interface AbilityCheckService {

    BigDecimal getAbilityAdvancementCoef(HeroCharacteristics.Ability first,
                                           HeroCharacteristics.Ability second);
    BigDecimal estimateAttackStrength(HeroCharacteristics characteristics);

    BigDecimal estimateDefenceStrength(HeroCharacteristics characteristics);

}
