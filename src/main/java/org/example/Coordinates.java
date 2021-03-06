package org.example;
import java.text.ParseException;


public class Coordinates {
    private long x; //Максимальное значение поля: 643
    private double y;

    public Coordinates(long x, double y) throws ParseException {
        if (y > 643) throw new ParseException("y не может быть больше 643");
        this.x = x;
        this.y = y;
    }
}
