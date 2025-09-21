package com.jaewan.gameoflife.view;

import com.jaewan.gameoflife.common.AnsiColors;
import com.jaewan.gameoflife.models.Coordinate;
import com.jaewan.gameoflife.models.Grid;
import com.jaewan.gameoflife.service.Simulation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Main 함수에서 호출(책임 분리)
// 사용자와의 소통 담당
public class ConsolView {
    // 사용자 선택
    private void showChoices(){
        System.out.println("1. 게임 시작");
        System.out.println("2. 게임 규칙 설명");
        System.out.println("3. 나가기");
        System.out.print(">>> ");
    }

    // 인생게임 전체 룰
    private void showRules(){
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

    }

    // 격자 입력 규칙
    private void showCoordinateInputRules(){
        System.out.println("현재 표시된 좌표의 모든 세포는 죽어있는 상태입니다..\n좌표를 입력하여 살리고싶은 첫 세대의 세포들를 지정해주세요!");
        System.out.println("더 이상 입력을 원치 않으면 q 혹은 Q를 입력해주세요!");
        System.out.println("다음처럼 입력해주시면 됩니다 ==> x, y >>> 1 3");
    }

    // 격자 변 크기 설정 후 좌표 선택 전 선택 가능한 좌표의 모음
    private void printGridCoordinates(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.printf(" (%-2d, %2d) ", (j + 1), (i + 1));
            }

            System.out.println();
        }
    }

    // 좌표 선택 시 q 입력 확인
    private boolean isQ(String str){
        return str.equals("q") || str.equals("Q");
    }

    // 좌표들을 입력 받고, 검증한 뒤, 좌표 리스트를 반환한다.
    private Set<Coordinate> inputCoordinates(int sideSize) throws NumberFormatException{
        Set<Coordinate> coordinates = new HashSet<>();
        Scanner scan = new Scanner(System.in);

        int x, y;
        while(true){
            System.out.print("x, y >>> ");
            String[] userCoordinateInput = scan.nextLine().trim().split(" ");

            // 입력 검사(문자열 길이 및 q, Q 입력 검사)
            int len = userCoordinateInput.length;

            if(len == 1){
                if(isQ(userCoordinateInput[0])){
                    System.out.println("좌표 입력 종료\n");
                    break;
                }
                System.out.println("하나의 수만 입력하셨습니다!");
                continue;
            } else if(len == 2){
                if(isQ(userCoordinateInput[0]) || isQ(userCoordinateInput[1])){
                    System.out.println("좌표 입력 종료\n");
                    break;
                }
            } else {
                System.out.println("두 숫자를 공백으로 분리하여 입력해주세요!(ex:3 5)");
                continue;
            }

            // 만약 parseInt 에서 예외 발생 시 NumberFormatException을 호출 블록으로 던짐
            x = Integer.parseInt(userCoordinateInput[0]);
            y = Integer.parseInt(userCoordinateInput[1]);

            // 입력 범위 검사
            if(!(checkNumberRange(1, sideSize, x) && checkNumberRange(0, sideSize, y))){
                System.out.println("좌표 x, y의 범위는 각각 1 이상 " + sideSize + " 이하여야 합니다!");
                continue;
            }

            Coordinate coordinate = new Coordinate(x, y);
            coordinates.add(coordinate);
        }

        return coordinates;
    }

    // 초기 세팅이 모두 끝난 뒤 Simulation, Grid 클래스와 상호작용하며 프로그램 시작
    private void startGame(int gridSideSize, Set<Coordinate> coordinates){
        System.out.println("게임시작");

        Simulation simulation = new Simulation(gridSideSize, coordinates);

        int count = 1;
        while(true){
            System.out.println("[세대 " + count++ + "]");

            Grid grid = simulation.getCurrentGrid();

            if(grid.isAllDead()){
                System.out.println("모든 세포가 죽었습니다..\n");
                break;
            }

            grid.printGrid();
            System.out.println();

            simulation.updateGrid();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 숫자 범위 확인
    private boolean checkNumberRange(int row, int high, int target){
        return (row <= target) && (target <= high);
    }

    // 사용자 1번 선택 시 초기 격자 크기 세팅
    private void initialSetting(){
        Scanner scan = new Scanner(System.in);

        System.out.print("한 변의 크기를 얼마로 하시겠습니까?(최대 15, 최소 3) >>> ");
        try {
            int side = Integer.parseInt(scan.nextLine().trim());

            // 한 변의 크기 검사
            if(!checkNumberRange(3, 15, side)){
                System.out.println("허용된 범위 내에서 입력해주십시오!\n");
                return;
            }

            // 입력받은 변을 기반으로 격자의 좌표 출력
            printGridCoordinates(side);

            // 좌표 입력 규칙
            showCoordinateInputRules();

            // side를 기반으로 사용자에게 여러 좌표를 입력받고 HashSet<Coordinate> 반환
            Set<Coordinate> coordinates = inputCoordinates(side);

            System.out.println("입력된 좌표는 다음과 같습니다.");
            for(Coordinate coordinate : coordinates)
                System.out.print("(" + coordinate.getX() + ", " + coordinate.getY() + ") ");
            System.out.print("\n시뮬레이션을 시작하시겠습니까?(y/n) >>> ");
            String startGameInput = scan.nextLine().trim();

            if(startGameInput.equals("y") || startGameInput.equals("Y")){
                // Simulation에 인수 전달
                System.out.println("인생게임을 시작하겠습니다!\n");

                startGame(side, coordinates);
            } else
                System.out.println("시작화면으로 돌아가겠습니다.\n");

        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요!\n");
        }
    }

    public void start(){
        System.out.println(AnsiColors.GREEN + "========================" + AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + "Welcome to Game Of Life!" +  AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + "========================" + AnsiColors.RESET);

        while (true) {
            showChoices();

            Scanner scan =  new Scanner(System.in);
            String choice = scan.nextLine().trim();

            switch (choice){
                case "1":
                    initialSetting();
                    break;
                case "2":
                    showRules();
                    break;
                case "3":
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다!");
                    break;
            }
        }
    }
}
