package com.jaewan.gameoflife;

import java.util.Set;

public class Simulation {
    Grid grid;

    public Simulation(int size){
        this.grid = new Grid(size);
        this.grid.initializeGrid();
    }

    public Simulation(int size, Set<Coordinate> coordinates) {
        this.grid = new Grid(size);
        this.grid.initializeGrid(); // 모든 Cell의 상태 = false로 초기화
        this.grid.applyCoordinates(coordinates); // 입력받은 coordinates를 기반으로 초기 true 값 설정
    }

    public Simulation(Grid grid) {
        this.grid = grid;
    }

    Grid nextGeneration(){
        int gridSize = this.grid.getGridSize();
        Grid nextGrid = new Grid(gridSize); // return할 nextGrid
        nextGrid.initializeGrid();

        // for문이 3번 중첩됌 -> 시간 복잡도 매우 비효율.. 인접 리스트 활용? 모든 좌표의 값을 살펴봐야 하기 때문에 불가피?
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                int statusCount = grid.countCoordinates(i, j, gridSize);
                boolean status = grid.nextGenStatus(i, j, statusCount);
                nextGrid.setGridValue(i, j, status);
            }
        }

        return nextGrid;
    }

    public Grid getCurrentGrid(){
        return this.grid;
    }

    public void updateGrid(){
        this.grid = nextGeneration();
    }
}
