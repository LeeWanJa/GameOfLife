package com.jaewan.gameoflife.models;

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
