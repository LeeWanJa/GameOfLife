package main.com.jaewan.gameoflife;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 사용자에게 격자의 크기 입력받기
        PlayingGame playingGame = new PlayingGame(8);

        // 입력받은 크기의 격자의 각 셀 이웃 세팅
        playingGame.setSimulation();
        
        // 사용자에게 시작 신호 받으면 실행
        playingGame.startOneSimulation();
    }
}