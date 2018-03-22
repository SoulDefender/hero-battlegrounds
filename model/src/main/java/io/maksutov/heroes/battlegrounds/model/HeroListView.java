package io.maksutov.heroes.battlegrounds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroListView {

    private List<Hero> heroes;

}
