package io.maksutov.heroes.battlegrounds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FightResult {

    private Hero winner;

    private Hero loser;

    @Max(100)
    private int probability;
}
