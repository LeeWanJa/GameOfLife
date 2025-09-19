package com.jaewan.gameoflife;

public class AnsiColors {
    // 색상 리셋
    public static final String RESET = "\033[0m";

    // 글자 색
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";

    // 커서를 (row, col) 위치로 이동시키는 메서드 (1, 1부터 시작)
    public static void moveCursor(int row, int col) {
        System.out.printf("\033[%d;%dH", row, col);
    }
}