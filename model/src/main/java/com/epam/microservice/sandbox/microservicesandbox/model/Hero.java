package com.epam.microservice.sandbox.microservicesandbox.model;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	
	private long ages;
	
	@Max(10000)
	private BigDecimal power;
	
	private HeroCharacteristics characteristics;
}
