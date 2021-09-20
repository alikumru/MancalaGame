package com.bol.mancalagame;

import com.bol.mancalagame.controller.GameController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MancalaGameApplicationTests {

    @InjectMocks
    private GameController mainController;


    @Test
    void contextLoads() {
    }

    @Test
    void GameLoad() {
    }

    @Test
    void move() {
    }

}
