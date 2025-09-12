package com.jaewan.gameoflife;

public class PlayingGame {
    final private Grid grid;

    PlayingGame(int size){
        this.grid = new Grid(size);
    }

    void setSimulation() {
        // Cell 들의 이웃 짝짓기
        grid.pairNeighbors();
    }

    boolean[][] startOneSimulation(boolean[][] temp){
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[i].length; j++){
                if(temp[i][j]){
                    System.out.println(i + "열 " + j + "번 인덱스 = true");
                }
            }
        }

        return temp;
    }
}
