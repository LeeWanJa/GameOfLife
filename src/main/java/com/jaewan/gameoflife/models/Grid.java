package com.jaewan.gameoflife.models;

import java.util.Set;

// 셀 정보 저장 도메인 객체 Grid
public class Grid {
    private Cell[][] grid;
    private int size;

    public Grid(int size){
        this.grid = new Cell[size][size];
        this.size = size;

        initializeGrid();
    }

    public Grid(int size, Set<Coordinate> coordinates){
        this.grid = new Cell[size][size];
        this.size = size;

        initializeGrid();
        applyCoordinates(coordinates);
    }

    // 초기화 함수
    private void initializeGrid(){
        // 각 격자마다 Cell 객체 생성
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    // 좌표에 해당되는 곳 true 변환
    private void applyCoordinates(Set<Coordinate> coordinates){
        // coordinates를 하나씩 돌면서 좌표에 해당되는 Cell의 status를 true로 변경
        for(Coordinate coordinate : coordinates)
            grid[coordinate.getY() - 1][coordinate.getX() - 1].setCellIsAlive(true);
    }

    // 요소 디버깅용
    public void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j].getCellIsAlive())
                    System.out.print("*  ");
                else
                    System.out.print("-  ");
            }

            System.out.println();
        }
    }

    // 특정 위치의 셀 값 반환
    public boolean getCellValue(int x, int y){
        return grid[x][y].getCellIsAlive();
    }

    // 특정 위치의 셀 값 변경
    private void setGridValue(int x, int y, boolean value){
        grid[x][y].setCellIsAlive(value);
    }

    public int getGridSize(){
        return this.size;
    }

    // 많은 값을 한 번에 바꾸기
    private void setGridValues(int[][] coordinates, boolean value){
        for(int[] offset : coordinates){
            grid[offset[0]][offset[1]].setCellIsAlive(value);
        }
    }

    // 좌표 계산
    private int countCoordinates(int i, int j, int size){
        int[][] result = {
                {i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1},
                {i, j - 1}, {i, j + 1}, // 자기 자신은 제외
                {i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1},
        };

        int count = 0;

        for(int[] offset : result){
            if((0 <= offset[0] && offset[0] < size) // y축의 범위 검사
                    && (0 <= offset[1] && offset[1] < size) // x축의 범위 검사
                    && getCellValue(offset[0], offset[1])){ // y, x축의 값이 true라면? -> 살아있는 세포
                count++;
            }
        }

        return count;
    }

    private boolean nextGenStatus(int i, int j, int neighborCount){
        boolean status = getCellValue(i, j);

        if(status) // 세포가 살아있는 경우
            return neighborCount == 2 || neighborCount == 3;
        else
            return neighborCount == 3;
    }

    // 그리스 상태 점검
    public boolean isAllDead(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j].getCellIsAlive())
                    return false;
            }
        }

        return true;
    }

    public Grid nextGeneration(){
        Grid nextGrid = new Grid(this.size); // return할 nextGrid

        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                int statusCount = countCoordinates(i, j, this.size);
                boolean status = nextGenStatus(i, j, statusCount);
                nextGrid.setGridValue(i, j, status);
            }
        }

        return nextGrid;
    }
}
