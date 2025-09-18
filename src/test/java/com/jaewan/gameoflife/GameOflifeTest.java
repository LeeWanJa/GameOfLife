package com.jaewan.gameoflife;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOflifeTest {
    @Test
    void nextGeneration_shouldRemainEmpty_whenGridIsEmpty(){
        /*
            Arrange(준비) - 모든 세포가 죽어있는 3*3 격자 표현
            Expected(기대) - 다음 세대에서도 모두 죽어있는 3*3 격자로 존재해야함
         */
        Simulation simulation = new Simulation(3);

        /*
            Act(실행) - 바로 다음 세대 계산
         */
        Grid nextGrid = simulation.nextGeneration();
        printGeneration(nextGrid);

        /*
            Assert(검증) - 기대하는 결과도 모든 세포가 죽어있는 격자여야 함
         */
        Grid expectedGrid = new Grid(nextGrid.getGridSize());
        expectedGrid.initializeGrid();

        // 두 Grid 객체가 같은지 비교한다
        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }

    // 디버깅 도구 -> 격자 True, False 출력
    void printGeneration(Grid grid){
        int size = grid.getGridSize();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid.getCellValue(i, j))
                    System.out.print("T ");
                else
                    System.out.print("F ");
            }

            System.out.println();
        }
    }

//    // [규칙 1] 탄생 : 죽어있는 세포의 살아있는 이웃이 정확히 3개이면, 다음 세대는 살아난다.
//    @Test
//    void nextGeneraton_shouldAlive_whenDeadCellHasThreeAliveNeighbors(){
//        Simulation simulation = new Simulation(3);
//        Grid nextGrid = simulation.nextGeneration();
//
//        printGeneration(nextGrid);
//
//        Grid expectedGrid = new Grid(3);
//        expectedGrid.initializeGrid();
//
//
//        assertArrayEquals(expectedGrid, nextGrid);
//    }
//
//    // [규칙 2] 생존 : 살아있는 칸의 이웃 중 살아있는 세포가 2개 혹은 3개이면, 다음 세대에도 살아난다.
//    @Test
//    void nextGeneration_shouldLiveCell_whenLiveCellHasTwoOrThreeNeighbors(){
//        // Arrange = 살아있는 중앙의 세포의 이웃은 두 개의 살아있는 세포를 이웃으로 두고 있음
//        boolean[][] initialGrid = {
//                {false, true, false,},
//                {false, true, false,},
//                {true, false, false,},
//        };
//
//        // Act(준비) - 다음 세대를 계산
//        Simulation simulation = new Simulation(initialGrid);
//        boolean[][] nextGrid = simulation.nextGeneration();
//
//        // Assert(검증) - 중앙을 제외한 모든 세포 사망
//        boolean[][] expectedGrid = {
//                {false, false, false,},
//                {false, true, false,},
//                {false, false, false,},
//        };
//
//        assertArrayEquals(expectedGrid, nextGrid);
//    }
//
//    // [규칙 3] 외로움 : 살아있는 칸의 이웃 중 살아있는 세포가 1개 이하이면, 너무 외로워서 죽는다.
//    @Test
//    void nextGneration_shouldKillCell_whenLiveCellHasZeroNeighbors(){
//        // Arrange(준비) - 중앙에 살아있는 세포가 딱 하나 있는 격자
//        boolean[][] initialGrid = {
//                {false, false, false,},
//                {false, true, false,},
//                {false, false, false,},
//        };
//
//        // Act(행위) - 다음 세대를 계산
//        Simulation simulation = new Simulation(initialGrid);
//
//        boolean[][] nextGrid = simulation.nextGeneration();
//
//        // 디버깅
//        printGeneration(nextGrid);
//
//        // Assert(검증) - 중앙의 세포가 죽어야 하므로, 모든 칸이 false인 격자를 기대
//        boolean[][] expectedGrid = {
//                {false, false, false,},
//                {false, false, false,},
//                {false, false, false,},
//        };
//
//        assertArrayEquals(expectedGrid, nextGrid);
//
//        // org.opentest4j.AssertionFailedError: array contents differ at index [1][1], expected: <false> but was: <true>
//        // 테스트 오류 발생! -> nextGeneration 메서드 로직 추가 필요
//    }
//
//    // [규칙 4] 과밀 : 살아있는 세포의 이웃이 4개 이상이면 죽음
//    @Test
//    void nextGeneration_shouldKillCell_whenLiveCellHasOverFourNeighbors(){
//        // Arrange(준비) - 주변에 살아있는 세포가 4개인 살아있는 격자
//        boolean[][] initialGrid = {
//                {false, true, false},
//                {true, true, true},
//                {false, true, false}
//        };
//
//        // Act(실행) - 다음 세대를 계산
//        Simulation simulation = new Simulation(initialGrid);
//        boolean[][] nextGrid = simulation.nextGeneration();
//
//        // Assertion(검증) - 중앙의 세포는 죽는다!
//        boolean[][] expectedGrid = {
//                {true, true, true},
//                {true, false, true},
//                {true, true, true},
//        };
//
//        assertArrayEquals(expectedGrid, nextGrid);
//    }


}