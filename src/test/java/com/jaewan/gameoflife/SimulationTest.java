package com.jaewan.gameoflife;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
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

    // [규칙 1] 탄생 : 죽어있는 세포의 살아있는 이웃이 정확히 3개이면, 다음 세대는 살아난다.
    @Test
    void nextGeneraton_shouldAlive_whenDeadCellHasThreeAliveNeighbors(){
        // Arrange
        Grid initialGrid = new Grid(3);
        initialGrid.initializeGrid();
        int[][] initialCoordinates = {{0, 0}, {1, 0}, {0, 1}};
        initialGrid.setGridValues(initialCoordinates, true);

        // Act
        Simulation simulation = new Simulation(initialGrid);
        Grid nextGrid = simulation.nextGeneration();
        printGeneration(nextGrid);

        // Assertion
        Grid expectedGrid = new Grid(3);
        expectedGrid.initializeGrid();
        int[][] expectedCoordinates = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        expectedGrid.setGridValues(expectedCoordinates, true);

        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }

    // [규칙 2] 생존 : 살아있는 칸의 이웃 중 살아있는 세포가 2개 혹은 3개이면, 다음 세대에도 살아난다.
    @Test
    void nextGeneration_shouldLiveCell_whenLiveCellHasTwoOrThreeNeighbors(){
        // Arrange
        Grid initialGrid = new Grid(3);
        initialGrid.initializeGrid();
        int[][] initialCoordinates = {{0, 2}, {1, 0}, {1, 1}};
        initialGrid.setGridValues(initialCoordinates, true);

        // Act
        Simulation simulation = new Simulation(initialGrid);
        Grid nextGrid = simulation.nextGeneration();

        // Assertion
        Grid expectedGrid = new Grid(3);
        expectedGrid.initializeGrid();
        int[][] expectedCoordinates = {{1, 1}, {0, 1}};
        expectedGrid.setGridValues(expectedCoordinates, true);

        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }

    // [규칙 3] 외로움 : 살아있는 칸의 이웃 중 살아있는 세포가 1개 이하이면, 너무 외로워서 죽는다.
    @Test
    void nextGneration_shouldKillCell_whenLiveCellHasZeroNeighbors(){
        // Arrange
        Grid initialGrid = new Grid(3);
        initialGrid.initializeGrid();
        initialGrid.setGridValue(1, 1, true);

        // Act
        Simulation simulation = new Simulation(initialGrid);
        Grid nextGrid = simulation.nextGeneration();

        // Assertion
        Grid expectedGrid = new Grid(3);
        expectedGrid.initializeGrid();

        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }

    // [규칙 4] 과밀 : 살아있는 세포의 이웃이 4개 이상이면 죽음
    @Test
    void nextGeneration_shouldKillCell_whenLiveCellHasOverFourNeighbors(){
        // Arrange
        Grid initialGrid = new Grid(3);
        initialGrid.initializeGrid();
        int[][] initialCoordinates = {{0, 1}, {1, 0}, {1, 1}, {2, 1}, {1, 2}};
        initialGrid.setGridValues(initialCoordinates, true);

        // Act
        Simulation simulation = new Simulation(initialGrid);
        Grid nextGrid = simulation.nextGeneration();

        // Assertion
        Grid expectedGrid = new Grid(3);
        expectedGrid.initializeGrid();
        int[][] expectedCoordinates = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
        };
        expectedGrid.setGridValues(expectedCoordinates, true);

        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }
}