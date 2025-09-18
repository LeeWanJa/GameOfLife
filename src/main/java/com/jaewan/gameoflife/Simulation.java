package com.jaewan.gameoflife;

public class Simulation {
    boolean[][] grid;

    public Simulation(boolean[][] grid) {
        this.grid = grid;
    }

    boolean[][] nextGeneration(){
        boolean[][] tmpGrid = new boolean[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                // 현재 셀의 주변 이웃 상태 점검(살아있는 수에 비례하여 count)
                int statusCount = 0;
                int[][] neighbors = {
                        {i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1},
                        {i, j - 1}, {i, j + 1}, // 자기 자신은 제외
                        {i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1},
                };

                for(int[] offset : neighbors){
                    if((0 <= offset[0] && offset[0] < grid.length) // y축의 범위 검사
                            && (0 <= offset[1] && offset[1] < grid[0].length) // x축의 범위 검사
                                && (grid[offset[0]][offset[1]])){ // y, x축의 값이 true라면? -> 살아있는 세포
                        statusCount++;
                    }
                }

                // 살아있는 세포와 죽어있는 세포로 조건 분리
                if(grid[i][j]){ // 세포가 살아있는 경우
                    if(statusCount == 2 || statusCount == 3)
                        tmpGrid[i][j] = true;
                    else
                        tmpGrid[i][j] = false;
                } else{
                    if(statusCount == 3)
                        tmpGrid[i][j] = true;
                    else
                        tmpGrid[i][j] = false;
                }
            }
        }

        return tmpGrid;
    }
}
