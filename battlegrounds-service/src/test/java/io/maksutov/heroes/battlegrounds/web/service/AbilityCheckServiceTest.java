package io.maksutov.heroes.battlegrounds.web.service;

import io.maksutov.heroes.battlegrounds.model.Environment;
import io.maksutov.heroes.battlegrounds.model.HeroCharacteristics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.maksutov.heroes.battlegrounds.model.HeroCharacteristics.Ability.DEXTERITY;
import static io.maksutov.heroes.battlegrounds.model.HeroCharacteristics.Ability.MAGIC_POWER;
import static io.maksutov.heroes.battlegrounds.model.HeroCharacteristics.Ability.STRENGTH;
import static io.maksutov.heroes.battlegrounds.model.HeroCharacteristics.Ability.WISDOM;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AbilityCheckServiceTest {

    private HeroCharacteristics heroCharacteristics;

    @Autowired
    private AbilityCheckService abilityCheckService;

    @Before
    public void setUp() {
        this.heroCharacteristics = new HeroCharacteristics(
                70,
                50,
                20,
                60,
                20,
                Arrays.asList(Environment.SNOW, Environment.RAINLANDS)
                );
    }

    @Test
    public void testThatGetAbilityAdvancementCoefReturnSuperiorityCoef() {
        BigDecimal expected = new BigDecimal("1.25");
        BigDecimal actual = this.abilityCheckService.getAbilityAdvancementCoef(DEXTERITY, STRENGTH);
        assertThat(actual, is(expected));
    }

    @Test
    public void testThatGetAbilityAdvancementCoefReturnDisadvantageCoef() {
        BigDecimal expected = new BigDecimal("0.75");
        BigDecimal actual = this.abilityCheckService.getAbilityAdvancementCoef(WISDOM, MAGIC_POWER);
        assertThat(actual, is(expected));
    }

    @Test
    public void testThatAttackStrengthEstimatedCorrectly() {
        BigDecimal expectedAttackStrengthThreshold = new BigDecimal("0.55");
        BigDecimal actualAttackStrength = this.abilityCheckService
                .estimateAttackStrength(heroCharacteristics);
        assertThat(actualAttackStrength, is(greaterThan(expectedAttackStrengthThreshold)));
    }

    @Test
    public void testThatDefenceStrengthEstimatedCorrectly() {
        BigDecimal expectedDefStrengthThreshold = new BigDecimal("0.65");
        BigDecimal actualDefStrength = this.abilityCheckService
                .estimateDefenceStrength(heroCharacteristics);
        assertThat(actualDefStrength, is(lessThan(expectedDefStrengthThreshold)));
    }
}