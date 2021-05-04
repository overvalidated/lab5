package org.germanbeyger.lab5.datatypes;


public class Location {
    private Double x; //Поле не может быть null
    private long y;
    private Double z; //Поле не может быть null

    public Location(Double x, long y, Double z) {
        if (x == null || z == null) throw new IllegalArgumentException("Fields x and z can't be null.");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", z='" + getZ() + "'" +
            "}";
    }

    public double getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }
}

