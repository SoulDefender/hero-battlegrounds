package com.epam.microservice.sandbox.microservicesandbox.model;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
