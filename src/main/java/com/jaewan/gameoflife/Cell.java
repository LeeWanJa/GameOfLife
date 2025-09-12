package com.jaewan.gameoflife;

import java.util.ArrayList;

public class Cell {
    // 현재 자신의 상태 -> true = 살아있음 / false = 죽어있음
    private final boolean status;
    private final int aliveNeighbors;
    // 인접행렬 방법 사용(빠르게 각 이웃에 접근해서 자신의 상태를 전달해야함)
    // 자신의 status == true 라면 이웃 모든 객체에 접근해 이웃의 aliveNeightbors +1
    private final ArrayList<Cell> neighbours;

    Cell() {
        this.status = false;
        this.aliveNeighbors = 0;
        neighbours = new ArrayList<>();
    }

    void addNeighbors(Cell cell){
        neighbours.add(cell);
    }

    boolean getCellStatus(){
        return status;
    }
}
