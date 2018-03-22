package io.maksutov.heroes.battlegrounds.web.service;

import io.maksutov.heroes.battlegrounds.model.HeroCharacteristics;

import java.math.BigDecimal;

public interface AbilityCheckService {

    BigDecimal getAbilityAdvancementCoef(HeroCharacteristics.Ability first,
                                           HeroCharacteristics.Ability second);
    BigDecimal estimateAttackStrength(HeroCharacteristics characteristics);

    BigDecimal estimateDefenceStrength(HeroCharacteristics characteristics);

}
