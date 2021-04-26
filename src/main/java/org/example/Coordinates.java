package org.example;


public class Coordinates {
    private Long x; //Максимальное значение поля: 643
    private Double y;

    public Coordinates(Long x, Double y) {
        if (x != null) {
            if (x > 643) throw new IllegalArgumentException("X must be less than 643");
        }
        this.x = x;
        this.y = y;
    }
}
