package com.jaewan.gameoflife.service;
import com.jaewan.gameoflife.models.Coordinate;
import com.jaewan.gameoflife.models.Grid;
import com.jaewan.gameoflife.service.Simulation;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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
        Grid initialGrid = simulation.getCurrentGrid();

        /*
            Act(실행) - 바로 다음 세대 계산
         */
        Grid nextGrid = initialGrid.nextGeneration();
        nextGrid.printGrid();

        /*
            Assert(검증) - 기대하는 결과도 모든 세포가 죽어있는 격자여야 함
         */
        Grid expectedGrid = new Grid(3);

        // 두 Grid 객체가 같은지 비교한다
        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }

    // [규칙 1] 탄생 : 죽어있는 세포의 살아있는 이웃이 정확히 3개이면, 다음 세대는 살아난다.
    @Test
    void nextGeneraton_shouldAlive_whenDeadCellHasThreeAliveNeighbors(){
        // Arrange
        Set<Coordinate> coordinates = new HashSet<Coordinate>();
        int[][] initialCoordinates = {{1, 1}, {2, 1}, {1, 2}};

        for(int[] offset : initialCoordinates)
            coordinates.add(new Coordinate(offset[0], offset[1]));

        Simulation simulation = new Simulation(3, coordinates);
        Grid initialGrid = simulation.getCurrentGrid();

        // Act
        Grid nextGrid = initialGrid.nextGeneration();

        // Assertion
        coordinates.clear();
        int[][] expectedCoordinates = {{1, 1}, {2, 1}, {1, 2}, {2, 2}};

        for(int[] offset: expectedCoordinates)
            coordinates.add(new Coordinate(offset[0], offset[1]));

        Simulation expectedSimulation = new Simulation(3, coordinates);
        Grid expectedGrid = expectedSimulation.getCurrentGrid();

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
        Set<Coordinate> coordinates = new HashSet<>();
        int[][] initialCoordinates = {{1, 3}, {2, 1}, {2, 2}};

        for(int[] offset : initialCoordinates)
            coordinates.add(new Coordinate(offset[0], offset[1]));

        Simulation simulation = new Simulation(3, coordinates);
        Grid initialGrid = simulation.getCurrentGrid();

        // Act
        Grid nextGrid = initialGrid.nextGeneration();

        // Assertion
        coordinates.clear();
        int[][] expectedCoordinates = {{2, 2}, {1, 2}};

        for(int[] offset: expectedCoordinates)
            coordinates.add(new Coordinate(offset[0], offset[1]));

        Simulation expectedSimulation = new Simulation(3, coordinates);
        Grid expectedGrid = expectedSimulation.getCurrentGrid();

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
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(2, 2));
        Simulation simulation = new Simulation(3, coordinates);
        Grid initialGrid = simulation.getCurrentGrid();

        // Act
        Grid nextGrid = initialGrid.nextGeneration();

        // Assertion
        Grid expectedGrid = new Grid(3);

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
        Set<Coordinate> coordinates = new HashSet<>();
        int[][] initialCoordinates = {{1, 2}, {2, 1}, {2, 2}, {3, 2}, {2, 3}};

        for(int[] offset : initialCoordinates)
            coordinates.add(new Coordinate(offset[0], offset[1]));

        Simulation simulation = new Simulation(3, coordinates);
        Grid initialGrid = simulation.getCurrentGrid();

        // Act
        Grid nextGrid = initialGrid.nextGeneration();

        // Assertion
        coordinates.clear();
        int[][] expectedCoordinates = {
                {1, 1}, {1, 2}, {1, 3},
                {2, 1}, {2, 3},
                {3, 1}, {3, 2}, {3, 3},
        };

        for(int[] offset: expectedCoordinates)
            coordinates.add(new Coordinate(offset[0], offset[1]));

        Simulation expectedSimulation = new Simulation(3, coordinates);
        Grid expectedGrid = expectedSimulation.getCurrentGrid();

        for(int i = 0; i < expectedGrid.getGridSize(); i++){
            for(int j = 0; j < expectedGrid.getGridSize(); j++){
                assertEquals(expectedGrid.getCellValue(i, j), nextGrid.getCellValue(i, j));
            }
        }
    }
}