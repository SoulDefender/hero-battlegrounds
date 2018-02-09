package com.epam.microservice.sandbox.microservicesandbox.model;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.util.streamex.MoreCollectors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Stream;

/**
 * @author Dmytro_Maksutov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroCharacteristics {

	@Size(min = 1, max = 100)
	private int strength;

	@Size(min = 1, max = 100)
	private int intelligence;

	@Size(min = 1, max = 100)
	private int wisdom;

	@Size(min = 1, max = 100)
	private int dexterity;

	@Size(min = 1, max = 100)
	private int magicPowerGrantedByUniverse;

	private List<Environment> preferredEnvironments = new ArrayList<>();

	public BigDecimal getDexterity() {
	    return BigDecimal.valueOf(dexterity);
    }

    public BigDecimal getStrength() {
        return BigDecimal.valueOf(strength);
    }

    public BigDecimal getIntelligence() {
        return BigDecimal.valueOf(intelligence);
    }

    public BigDecimal getWisdom() {
        return BigDecimal.valueOf(wisdom);
    }

    public BigDecimal getMagicPowerGrantedByUniverse() {
        return BigDecimal.valueOf(magicPowerGrantedByUniverse);
    }

    public Ability getLeadingAbility() {
        OptionalLong maxIndex = Stream.of(
                strength, intelligence, wisdom, dexterity,
                magicPowerGrantedByUniverse).collect(MoreCollectors.maxIndex());

        OptionalLong minIndex = Stream.of(
                strength, intelligence, wisdom, dexterity,
                magicPowerGrantedByUniverse).collect(MoreCollectors.minIndex());
        if (minIndex.equals(maxIndex)) {
            return Ability.MAXIMA;
        }
        int ability = (int) maxIndex.orElse(0);
        return Ability.values()[ability];
    }

    public enum Ability {
	    STRENGTH,
        INTELLIGENCE,
        WISDOM,
        DEXTERITY,
        MAGIC_POWER,
        MAXIMA
    }
}
