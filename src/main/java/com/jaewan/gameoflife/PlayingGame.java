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

    Grid startOneSimulation(){
        for(int i = 0; i < grid.getRowSize(); i++){
            for(int j = 0; j < grid.getColumnSize(); j++){
                if(grid.getCellStatus(i, j)){
                    System.out.println(i + "열 " + j + "번 인덱스 = true");
                }
            }
        }

        return grid;
    }
}
