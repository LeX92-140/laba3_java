package ru.denisov.geometry;

public final class Point3D extends Point implements Cloneable { //Трехмерная точка. Подкласс Point. Реализует клонирование (возвращает новый объект с копией координат).
    private double z;

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public Point3D() {
        this(0.0, 0.0, 0.0);
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public Point3D clone() { // возвращаем новую независимую точку
        return new Point3D(getX(), getY(), z);
    }

    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }
}