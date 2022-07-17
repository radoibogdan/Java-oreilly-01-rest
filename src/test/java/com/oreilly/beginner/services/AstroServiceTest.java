package com.oreilly.beginner.services;

import com.oreilly.beginner.json.Assignment;
import com.oreilly.beginner.json.AstroResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AstroServiceTest {

    @Autowired
    private AstroService service;

    @Test
    void getAstronautRestTemplate() {
        AstroResult result = service.getAstronautsUsingRestTemplate();
        int number = result.getNumber();
        System.out.println("There are " + number + " people in space");
        List<Assignment> people = result.getPeople();
        people.forEach(System.out::println);
        assertAll(
                () -> assertTrue(number >= 0),
                () -> assertEquals(number, people.size())
        );
    }

    @Test
    void getAstronautWebClient() {
        AstroResult result = service.getAstronautsUsingWebClient();
        int number = result.getNumber();
        System.out.println("There are " + number + " people in space");
        List<Assignment> people = result.getPeople();
        people.forEach(System.out::println);
        assertAll(
                () -> assertTrue(number >= 0),
                () -> assertEquals(number, people.size())
        );
    }
}
