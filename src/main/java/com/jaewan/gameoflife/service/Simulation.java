package com.jaewan.gameoflife.service;

import com.jaewan.gameoflife.models.Coordinate;
import com.jaewan.gameoflife.models.Grid;

import java.util.Set;

public class Simulation {
    private Grid grid;

    // 테스트에 사용
    public Simulation(int size){
        this.grid = new Grid(size);
    }

    public Simulation(int size, Set<Coordinate> coordinates) {
        this.grid = new Grid(size, coordinates);
    }

    // 테스트에 사용
    public Simulation(Grid grid) {
        this.grid = grid;
    }

    // 현재 격자 반환
    public Grid getCurrentGrid(){
        return this.grid;
    }

    // 미래의 격자로 업그레이드 -> Grid에게 요청
    public void updateGrid(){
        this.grid = grid.nextGeneration();
    }
}
