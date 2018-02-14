package com.epam.microservice.sandbox.microservicesandbox;

import com.epam.microservice.sandbox.microservicesandbox.model.Hero;
import com.epam.microservice.sandbox.microservicesandbox.model.HeroCharacteristics;
import com.epam.microservice.sandbox.microservicesandbox.model.OpponentHeroes;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.epam.microservice.sandbox.microservicesandbox.TestHelper.asJsonString;
import static com.epam.microservice.sandbox.microservicesandbox.model.Environment.CITY;
import static com.epam.microservice.sandbox.microservicesandbox.model.Environment.FIELD;
import static com.epam.microservice.sandbox.microservicesandbox.model.Environment.HILLS;
import static com.epam.microservice.sandbox.microservicesandbox.model.Environment.MOUNTAINS;
import static com.epam.microservice.sandbox.microservicesandbox.model.Environment.RAINLANDS;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Dmytro_Maksutov
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BattlegroundsApplication.class})
public class BattlegroundsApplicationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private OpponentHeroes opponentHeroes;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Hero hero = new Hero("1", "Saitama", "Bald Cape", 27, 71, 177, null,
                new HeroCharacteristics(
                        100,
                        100,
                        100,
                        100,
                        100,
                        Arrays.asList(CITY, HILLS, MOUNTAINS, RAINLANDS, FIELD)

                ));
        Hero opponent = new Hero("2", "Garo", "Human Monster", 25, 77, 184, null,
                new HeroCharacteristics(
                        75,
                        60,
                        50,
                        89,
                        20,
                        Arrays.asList(CITY, FIELD)
                ));

        opponentHeroes = new OpponentHeroes(hero, opponent, CITY);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testThatSuperiorHeroWins() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/battlegrounds/sparring")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(opponentHeroes))
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.winner.id", is("1")))
                .andExpect(jsonPath("$.loser.id", is("2")))
                .andExpect(jsonPath("$.probability", is(Matchers.greaterThan(90))));
    }

    @Test
    public void testThatInputValidationIsWorking() throws Exception {
        opponentHeroes.setFirstHero(new Hero(
                null,
                "das",
                "sad",
                -100,
                -100,
                -1,
                null,
                null
        ));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/battlegrounds/sparring")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(opponentHeroes))
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());
    }
}
