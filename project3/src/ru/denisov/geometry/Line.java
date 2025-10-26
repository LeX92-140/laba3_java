package ru.denisov.geometry;

public class Line { //Линия между двумя точками
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("начало и конец должны быть не null");
        }
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        if (start == null) {
            throw new IllegalArgumentException("начало не может быть null");
        }
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        if (end == null) {
            throw new IllegalArgumentException("конец не может быть null");
        }
        this.end = end;
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}