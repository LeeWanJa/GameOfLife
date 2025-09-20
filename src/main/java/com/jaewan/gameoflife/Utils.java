package com.jaewan.gameoflife;

public class Utils {
    // 입력받은 격자 화면에 듸우기
    public static void printGrid(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.printf(" (%-2d, %2d) ", (j + 1), (i + 1));
            }

            System.out.println();
        }
    }

    // 정수 범위 검사
    public static boolean checkNumberRange(int row, int high, int target){
        boolean result = (row <= target) && (target <= high);

        return result;
    }
}
