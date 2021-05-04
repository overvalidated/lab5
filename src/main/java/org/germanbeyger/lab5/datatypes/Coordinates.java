package org.germanbeyger.lab5.datatypes;


public class Coordinates{
    private long x; //Максимальное значение поля: 643
    private double y;

    public Coordinates(long x, double y) {
        if (x > 643) throw new IllegalArgumentException("X must be less or equal to 643");
        
        this.x = x;
        this.y = y;
    }

    public boolean verify() {
        return x <= 643;
    }


    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            "}";
    }

    public long getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
    
}
