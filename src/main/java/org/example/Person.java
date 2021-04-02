package org.example;

public class Person {
    private float height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    public Person(float height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        if (height <= 0) throw new IllegalArgumentException("Height must be positive");
        if (eyeColor == null) throw new IllegalArgumentException("Eye color can't be missing value");
        if (hairColor == null) throw new IllegalArgumentException("Hair can't be missing value");
        if (nationality == null) throw new IllegalArgumentException("Nationality can't be missing value");
        if (location == null) throw new IllegalArgumentException("Location can't be missing value");

        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }
}