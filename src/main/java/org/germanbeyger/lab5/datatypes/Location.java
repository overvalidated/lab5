package org.germanbeyger.lab5.datatypes;


public class Location {
    private double x; //Поле не может быть null
    private Long y;
    private double z; //Поле не может быть null

    public Location(double x, Long y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

