package com.epam.microservice.sandbox.microservicesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

	@Min(0)
	private long weight;

	@Min(0)
	private long height;

	@URL
	private String portraitUrl;

	@Setter
	@NotNull
	private HeroCharacteristics characteristics;

	public Optional<HeroCharacteristics> getCharacteristics() {
		return Optional.ofNullable(characteristics);
	}
}
