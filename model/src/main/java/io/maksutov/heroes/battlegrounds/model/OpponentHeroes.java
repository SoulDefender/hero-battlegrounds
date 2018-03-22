package io.maksutov.heroes.battlegrounds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpponentHeroes {

    @NotNull
    private Hero hero;

    @NotNull
    private Hero opponent;

    @NotNull
    private Environment battleground;

}
