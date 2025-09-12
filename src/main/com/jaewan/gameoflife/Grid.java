package main.com.jaewan.gameoflife;

import java.util.ArrayList;

public class Grid {
    private final Cell[][] cells;

    Grid(int size) {
        this.cells = new Cell[size][size];
    }

    void pairNeighbors(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                // x축 -1
                if(i - 1 >= 0){
                    cells[i][j].addNeighbors(cells[i - 1][j]);

                    if(j - 1 >= 0)
                        cells[i][j].addNeighbors(cells[i - 1][j - 1]);

                    if(j + 1 < cells[i].length)
                        cells[i][j].addNeighbors(cells[i - 1][j + 1]);
                }

                // x축 +1
                if(i + 1  < cells.length){
                    cells[i][j].addNeighbors(cells[i + 1][j]);

                    if(j - 1 >= 0)
                        cells[i][j].addNeighbors(cells[i + 1][j - 1]);

                    if(j + 1 < cells[i].length)
                        cells[i][j].addNeighbors(cells[i + 1][j + 1]);
                }

                // y축 -1
                if(j - 1 >= 0)
                    cells[i][j].addNeighbors(cells[i][j - 1]);

                // y축 +1
                if(j + 1 < cells[i].length)
                    cells[i][j].addNeighbors(cells[i][j + 1]);
            }
        }
    }
}
