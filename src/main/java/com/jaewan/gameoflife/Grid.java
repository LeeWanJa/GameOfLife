package com.jaewan.gameoflife;

// 셀 정보 저장 도메인 객체 Grid
public class Grid {
    private final Cell[][] grid;

    Grid(int size){
        grid = new Cell[size][size];
    }

    // 초기화 함수
    void initializeGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    // 요소 디버깅용
    void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j].getCellIsAlive())
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
        return grid[x][y].getCellIsAlive();
    }

    // 특정 위치의 셀 값 변경
    void setGridValue(int x, int y, boolean value){
        grid[x][y].setCellIsAlive(value);
    }

    // 많은 값을 한 번에 바꾸기
    void setGridValues(int[][] coordinates, boolean value){
        for(int[] offset : coordinates){
            grid[offset[0]][offset[1]].setCellIsAlive(value);
        }
    }

    // 좌표 계산
    int countCoordinates(int i, int j, int size){
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

    boolean nextGenStatus(int i, int j, int neighborCount){
        boolean status = getCellValue(i, j);

        if(status){ // 세포가 살아있는 경우
            if(neighborCount == 2 || neighborCount == 3)
                return true;
            else
                return false;
        } else{
            if(neighborCount == 3)
                return true;
            else
                return false;
        }
    }
}
