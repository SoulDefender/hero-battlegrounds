package com.epam.microservice.sandbox.microservicesandbox.web.service;

import com.epam.microservice.sandbox.microservicesandbox.model.Environment;
import com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.*;
import static com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics.Ability.*;
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