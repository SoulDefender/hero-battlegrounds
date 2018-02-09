package com.epam.microservice.sandbox.microservicesandbox.model;

import java.math.BigDecimal;
import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author Dmytro_Maksutov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
	
	private String id;
	
	@Length(min = 3)
	private String name;
	
	@Length(min = 3)
	private String title;

	@Min(0)
	private long ages;

	@Setter
	@NotNull
	private HeroCharacteristics characteristics;

	public Optional<HeroCharacteristics> getCharacteristics() {
		return Optional.ofNullable(characteristics);
	}
}
