package main.com.jaewan.gameoflife;

public class PlayingGame {
    final private Grid grid;

    PlayingGame(int size){
        this.grid = new Grid(size);
    }

    void setSimulation() {
        // 각 Cell 들의 이웃 짝짓기
        grid.pairNeighbors();
    }

    void startOneSimulation(){

    }
}
