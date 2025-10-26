package ru.denisov.transport;

import java.util.Map;

public final class TwoWayCity extends City { //двусторонняя дорога

    public TwoWayCity(String name) {
        super(name);
    }

    public TwoWayCity(String name, Map<City, Integer> initial) {
        super(name, initial);
    }

    @Override
    public void addConnection(City to, int cost) {
        super.addConnection(to, cost);
        try {
            to.addConnection(this, cost);
        } catch (IllegalStateException | IllegalArgumentException e) {}
    }
}