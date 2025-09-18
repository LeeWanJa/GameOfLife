package com.jaewan.gameoflife;

// 셀 정보 저장 도메인 객체 Grid
public class Grid {
    private final boolean[][] grid;

    Grid(int size){
        grid = new boolean[size][size];
    }

    // 초기화 함수
    void initializeGrid(){
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                grid[i][j] = false;
            }
        }
    }

    // 요소 디버깅용
    void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j])
                    System.out.print("T ");
                else
                    System.out.print("F ");
            }

            System.out.println();
        }
    }

    // 정사각형 그리드의 변 크기
    int getGridSize(){
        return grid.length;
    }

    // 특정 위치의 셀 값 반환
    boolean getCellValue(int x, int y){
        return grid[x][y];
    }

    // 특정 위치의 셀 값 변경
    void setGridValue(int x, int y, boolean value){
        grid[x][y] = value;
    }
}
