package com.epam.microservice.sandbox.microservicesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpponentHeroes {

    @NotNull
    private Hero firstHero;

    @NotNull
    private Hero secondHero;

    @NotNull
    private Environment battleground;

}
