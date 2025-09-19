package com.jaewan.gameoflife;

public class Utils {
    // 입력받은 격자 화면에 듸우기
    public static void printGrid(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.printf(" (%-2d, %2d) ", (i + 1), (j + 1));
            }

            System.out.println();
        }
    }
}
