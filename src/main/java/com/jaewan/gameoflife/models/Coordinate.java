package com.jaewan.gameoflife.models;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // equals, hashCode 오버라이딩

    // 1. equals() : 두 객체가 언제 같은가에 대한 새로운 규칙
    @Override
    public boolean equals(Object obj) {
        // (1)메모리 주소가 같은 경우
        if(this == obj)
            return true;

        // (2)비교할 객체가 null 이거나 클래스 종류가 다른 경우
        if(obj == null || getClass() != obj.getClass())
            return false;

        // (3)x와 y의 필드 값을 직접 비교
        Coordinate that =  (Coordinate) obj;
        return this.x == that.x && this.y == that.y;
    }

    // 2. hashCode() 재정의 : 객체가 저장될 메모리의 장소를 어떻게 계산할까?
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
