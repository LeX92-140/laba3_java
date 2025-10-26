package ru.denisov.geometry;

public class Point { //Инкапсуляция приватные поля геттеры сеттеры.
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + trim(x) + ";" + trim(y) + "}";
    }

    private String trim(double v) {
        if (v == (long) v) {
            return String.format("%d", (long) v);
        }
        return String.format("%s", v);
    }
}