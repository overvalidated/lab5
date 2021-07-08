package org.germanbeyger.lab5.datatypes;

import java.io.Serializable;

public class Coordinates implements Serializable, Comparable<Coordinates> {
    private static final long serialVersionUID = 1L;
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

    public int compareTo(Coordinates b) {
        if (this.x - b.x == 0 ) {
            if (this.y - b.y == 0) return 0;
             else if (this.y - b.y > 0) return 1;
             else return -1;
        } 
        else return this.x - b.x > 0 ? 1 : -1;
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
