package com.jaewan.gameoflife;
import org.junit.jupiter.api.Test;

public class GameOflifeTest {
    @Test
    void nextGeneration_shouldRemainEmpty_whenGridIsEmpty(){
        /*
            Arrange(준비) - 모든 세포가 죽어있는 3*3 격자 표현
            아직 격자는 없음
         */


        /*
            Act(실행) - 바로 다음 세대 계산
         */
        PlayingGame playingGame = new PlayingGame(3);
        Grid testGrid = playingGame.startOneSimulation();
    }
}
