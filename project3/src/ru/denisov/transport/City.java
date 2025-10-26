package ru.denisov.transport;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City { //Город с наборами путей к другим городам односторонняя дорога
    private final String name;
    private final Map<City, Integer> connections;

    public City(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название города не может быть пустым");
        }
        this.name = name.trim();
        this.connections = new HashMap<>();
    }

    public City(String name, Map<City, Integer> initialConnections) { //Конструктор, где сразу можно задать связи
        this(name);
        if (initialConnections != null) {
            this.connections.putAll(initialConnections); //одна дорога
        }
    }

    public String getName() {
        return name;
    }

    public void addConnection(City to, int cost) { //Добавляет дорогу из этого города в другой. Если дорога уже есть — бросаем исключение
        if (to == null) throw new IllegalArgumentException("Нужный город не может быть null");
        if (cost < 0) throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        if (connections.containsKey(to)) {
            throw new IllegalStateException("Дорога из " + name + " в " + to.name + " уже существует");
        }
        connections.put(to, cost);
    }

    public boolean removeConnection(City to) { //Удаляет дорогу если нет — возвращаем false
        if (to == null) return false;
        return connections.remove(to) != null;
    }

    public Map<City, Integer> getConnections() { //Возвращает неизменяемый вид связей
        return Collections.unmodifiableMap(connections);
    }

    @Override
    public String toString() {
        if (connections.isEmpty()) {
            return name + " (нет путей)";
        }
        StringBuilder sb = new StringBuilder(name + " - ");
        connections.forEach((city, cost) -> sb.append(city.name).append("(").append(cost).append(") "));
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object l) {
        if (this == l) return true;
        if (!(l instanceof City)) return false;
        City other = (City) l;
        if (this.connections.size() != other.connections.size()) return false;
        Map<String, Integer> otherMap = new HashMap<>();
        for (Map.Entry<City, Integer> e : other.connections.entrySet()) {
            otherMap.put(e.getKey().name, e.getValue());
        }
        for (Map.Entry<City, Integer> e : this.connections.entrySet()) {
            String destName = e.getKey().name;
            Integer cost = e.getValue();
            if (!Objects.equals(otherMap.get(destName), cost)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}