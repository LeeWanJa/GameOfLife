package com.jaewan.gameoflife;

public class Cell {
    private boolean status;

    Cell() {
        this.status = false;
    }

    boolean getCellIsAlive() {
        return this.status;
    }

    void setCellIsAlive(boolean status) {
        this.status = status;
    }
}
