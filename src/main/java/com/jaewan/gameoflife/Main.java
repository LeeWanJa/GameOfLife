package com.jaewan.gameoflife;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(AnsiColors.GREEN + "========================" + AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + "Welcome to Game Of Life!" +  AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + "========================" + AnsiColors.RESET);

        while (true) {
            System.out.println("1. 게임 시작");
            System.out.println("2. 게임 규칙 설명");
            System.out.println("3. 나가기");
            System.out.print(">>> ");

            Scanner scan =  new Scanner(System.in);
            String choice = scan.nextLine().trim();

            if(choice.equals("1")) {
                System.out.print("한 변의 크기를 얼마로 하시겠습니까?(최대 15, 최소 3) >>> ");
                try {
                    int size = Integer.parseInt(scan.nextLine().trim());

                    if(size > 15 || size < 3){
                        System.out.println("허용된 범위 내에서 입력해주십시오!\n");
                        continue;
                    }

                    Utils.printGrid(size);

                    System.out.println("현재 표시된 좌표의 모든 세포는 죽어있는 상태입니다..\n좌표를 입력하여 살리고싶은 첫 세대의 세포들를 지정해주세요!");
                    System.out.println("(입력 예 : >>> 1, 1");

                    while(true){
                        System.out.println(">>> ");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요!\n");
                }

            } else if(choice.equals("2")) {
                System.out.println("https://codingdojo.org/kata/GameOfLife/");
                System.out.println(AnsiColors.RED + "<<카타의 인생게임>>" + AnsiColors.RESET);
                System.out.println(AnsiColors.RED + "[인생게임이란?]" + AnsiColors.RESET);
                System.out.println("플레이어가 조종하지 않고, 단순한 규칙에 따라 생명체들이 태어나고 죽는 패턴을 관찰하는 시뮬레이션이다.");
                System.out.println("바둑판처럼 생긴 2차원 격자에서 진행한다. 각 칸은 '살아있음' 혹은 '죽어있음' 중 한 가지 상태를 택한다.");
                System.out.println(AnsiColors.RED + "[규칙]" + AnsiColors.RESET);
                System.out.println("현재 세대의 상태를 기반으로 다음 세대의 상태를 계산한다.\n" +
                        "한 세포의 상태와 그 세포를 둘러싼 8개의 세포 중 살아있는 세포가 몇 개인지를 기준으로 다음 세대로 넘어갈 때 자신의 상태가 정해진다.");
                System.out.println("규칙 1 - 탄생 : 죽어있는 칸의 이웃 중 살아있는 세포가 정확히 3개이면, 다음 세대에 새로운 세포가 태어난다.");
                System.out.println("규칙 2 - 생존 : 살아있는 칸의 이웃 중 살아있는 세포가 2개 혹은 3개이면, 다음 세대에도 살아난다.");
                System.out.println("규칙 3 - 외로움 : 살아있는 칸의 이웃 중 살아있는 세포가 1개 이하이면, 너무 외로워서 죽는다.");
                System.out.println("규칙 4 - 과밀 : 살아있는 칸의 이웃 중 살아있는 세포가 4개 이상이면, 너무 복잡해서 다음 세대에 죽는다.");
                System.out.println(AnsiColors.RED + "[진행]" + AnsiColors.RESET);
                System.out.println(AnsiColors.RED + "정사각형 격자의 크기를 입력하신 뒤, 살아있길 바라는 격자의 요소를 직접 지정해주세요!" + AnsiColors.RESET);
                System.out.println();
                
            } else if(choice.equals("3")) {
                System.out.println("프로그램 종료");
                return;
            } else {
                System.out.println("잘못된 입력입니다!");
            }
        }
    }
}
