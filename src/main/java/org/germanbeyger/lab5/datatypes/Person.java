package org.germanbeyger.lab5.datatypes;

import java.io.Serializable;

public class Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private float height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color2 hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    public Person(float height, Color eyeColor, Color2 hairColor, Country nationality, Location location) {
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

    public boolean verify() {
        if (height <= 0 || eyeColor == null || hairColor == null || nationality == null || location == null) return false;
        return getLocation().verify();
    }

    public float getHeight() {
        return this.height;
    }

    public Color getEyeColor() {
        return this.eyeColor;
    }

    public Color2 getHairColor() {
        return this.hairColor;
    }

    public Country getNationality() {
        return this.nationality;
    }

    public Location getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return "{" +
            " height='" + getHeight() + "'" +
            ", eyeColor='" + getEyeColor() + "'" +
            ", hairColor='" + getHairColor() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }

}