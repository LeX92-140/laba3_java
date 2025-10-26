# Проект java/ Лабораторная работа №3. Выполнил Денисов Алексей ИТ-13

## Описание проекта
Проект представляет собой реализацию 11 задач по объектно-ориентированному программированию на Java с использованием продвинутых концепций ООП: инкапсуляция, наследование, полиморфизм, пакеты. Все классы организованы в логическую структуру пакетов.

## Структура проекта
### Задание 1: Дороги (Инкапсуляция)
```
public class City {
    private final String name;
    private final Map<City, Integer> connections;
    
    public void addConnection(City to, int cost) {
        if (connections.containsKey(to)) {
            throw new IllegalStateException("Дорога уже существует");
        }
        connections.put(to, cost);
    }
    
    public boolean removeConnection(City to) {
        return connections.remove(to) != null;
    }
}
```
**Описание:** Класс City гарантирует уникальность дорог между городами. Реализована строгая инкапсуляция с приватными полями, геттерами и проверками через исключения.

**Вход:** 
```
City A = new City("A");
City B = new City("B");
A.addConnection(B, 5);
A.addConnection(B, 3); // повторное добавление
```
**Вывод:**
Ожидаемая ошибка при добавлении дубля дороги: Дорога из A в B уже существует

### Задание 2: Маршрут (Структуры данных)
```
public class Route {
    private City start;
    private City end;
    
    public City[] getPath() {
        if (start.equals(end)) return new City[]{start};
        Queue<City> q = new ArrayDeque<>();
        Map<City, City> prev = new HashMap<>();
        // BFS алгоритм поиска пути
        q.add(start);
        while (!q.isEmpty()) {
            City cur = q.poll();
            for (City neighbor : cur.getConnections().keySet()) {
                if (!prev.containsKey(neighbor)) {
                    prev.put(neighbor, cur);
                    if (neighbor.equals(end)) {
                        return reconstructPath(prev, end);
                    }
                    q.add(neighbor);
                }
            }
        }
        return new City[0];
    }
}
```
**Описание:** Класс Route представляет маршрут между городами с алгоритмом BFS для поиска пути. Изменение точек начала и конца выполняется за O(1).

**Вход:** 
```
Route route = new Route(F, D);
System.out.println("Маршрут Вывод:+ route);
```
**Вывод:**
Маршрут F - D: F - A - B - D
Путь как массив: F A B D

### Задание 3: Двусторонняя дорога (Наследование)
```
public final class TwoWayCity extends City {
    public TwoWayCity(String name) {
        super(name);
    }
    
    @Override
    public void addConnection(City to, int cost) {
        super.addConnection(to, cost);
        to.addConnection(tОписание:   }
}
```
**Описание:** Класс TwoWayCity наследует от City и гарантирует создание двусторонних дорог. Использует `extends`, `super` и `final`.

**Вход:**
``` 
City A = new TwoWayCity("A");
City B = new TwoWayCity("B");
```
**Вывод:**
A - B(1)
B - A(1)

### Задание 4: Трехмерная точка (Наследование)
```
public final class Point3D extends Point implements Cloneable {
    private double z;
    
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
    
    @Override
    public Point3D clone() {
        return new Point3D(getXОписание:);
    }
}
```
**Описание:** Класс Point3D расширяет функциональность Point добавлением третьей координаты Z. Реализует интерфейс Cloneable для поддержки клонирования.

**Вход:**
```
Point3D p3d = new Point3D(1, 2, 3);
System.out.println("3D точка: " + p3d);
System.out.println("КлонВывод:" + p3d.clone());
```
**Вывод:**
3D точка: {1.0;2.0;3.0}
Клонирование: {1.0;2.0;3.0}

### Задание 5: Птицы (Абстрактные классы)
```
public abstract class Bird {
    private final String species;
    public abstract void sing();
}

public class Sparrow extends Bird {
    public Sparrow() { super("Sparrow"); }
    @Override
    public void sing() { System.out.println("чырык"); }
}
public class Cuckoo extends Bird {
    @Override
    public void sing() {
        int times = ThreadLocalRandom.current().nextInt(1, 11);
        for (int i = 0; i < times; i++) {
            System.out.print("ку-ку ");
        }
        System.out.println();
    }
}

public class Parrot extends Bird {
    private final String text;
    public Parrot(String text) {
        super("Parrot");
        this.text = text;
    }
    @Override
    public void sing() {
        int n = ThreadLocalRandom.current().nextInt(1, text.length() + 1);
        System.out.println(text.substring(0, n));
    }
}
```
**Описание:** Создана иерархия птиц с абстрактным классом Bird и конкретными реализациями. Каждая птица имеет уникальное поведение пения.

**Вход:**
```
Bird sparrow = new Sparrow();
Bird cuckoo = new Cuckoo();
Bird parrot = new Parrot("Привет всем я глупый");
sparrow.sing();
cuckoo.sing();
parrot.sing();
```
**Вывод:**
чырык
ку-ку ку-ку ку-ку ку-ку 
Привет всем я глу

### Задание 6: Городим (Полиморфизм)
```
// В Main.java
City A = new TwoWayCity("A");
City B = new TwoWayCity("B");
City C = new City("C");
City D = new City("D");
City E = new City("E");
City F = new City("F");

A.addConnection(B, 1);
B.addConnection(C, 1);
A.addConnection(C, 1);
C.addConnection(E, 1);
E.addConnection(D, 1);
D.addConnection(B, 1);
F.addConnection(A, 2);
```
**Описание:** Реализована схема городов с использованием полиморфизма - совместная работа City и TwoWayCity в одной системе.

**Вход:** Создание смешанной сети городов
**Вывод:**
A - B(1) C(1)
B - A(1) C(1) 
C - E(1)
D - B(1)
E - D(1)
F - A(2)

### Задание 7: Сравнение городов (equals)
```
@Override
public boolean equals(Object o) {
    if (!(o instanceof City)) return false;
    City other = (City) o;
    if (this.connections.size() != other.connections.size()) return false;
    // Сравнение по наборам путей
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
    return true;
}
```
**Описание:** Переопределен метод equals для сравнения городов по их связям с другими городами.

**Вход:**
```
City X1 = new City("X1");
City X2 = new City("X2");
City Z = new City("Z");
X1.addConnection(Z, 10);
X2.addConnection(Z, 10);
System.out.println("X1.equalВывод:+ X1.equals(X2));
```
**Вывод:**
Х1 равен городу Х2 - true

### Задания 8-9: Организация пакетов
src/main/java/ru/denisov/
├─ geometry/Point.java
├─ geometry/Point3D.java
├─ geometry/Line.java
├─ person/Name.java
├─ transport/City.java
├─ transport/TwoWayCity.java
├─ transport/Route.java
├─ birds/Bird.java
├─ birds/Sparrow.java
├─ birds/Cuckoo.java
├─ birds/Parrot.java
├─ math/FracОписание:main/Main.java
**Описание:** Все классы организованы в логические пакеты с трехсоставными именами. Главный метод находится только в пакете ru.ivanov.main.

### Задание 10: Возведение в степень
```
System.out.print("Введите X (целое): ");
String xs = scanner.nextLine().trim();
System.out.print("Введите Y (целое): ");
String ys = scanner.nextLine().trim();
int X = Integer.parseInt(xs);
int Y = Integer.parseInt(ys);
double pow = Math.pow(X, Y);
System.out.println(Описание: " = " + pow);
```
**Описание:** Реализован метод с использованием статических импортов и ввода с клавиатуры.

**Вход:**
Введите X Вывод:
Введите Y (целое): 8
**Вывод:**
2^8 = 256.0

### Задание 11: Клонирование точки
```
@Override
public Point3D clone() {
    return new Описание:), getY(), z);
}
```
**Описание:** Переопределен метод clone() для создания независимых копий объектов Point3D.

**Вход:**
```
Point3D original = new Point3D(5, 10, 15);
Point3D cloned = original.clone();
cloned.setZ(100);
System.out.println("Оригинал: " + original);
System.out.println("Клон: " + cloned);
```
**Вывод:**
Оригинал: {5.0;10.0;15.0}
Клон: {5.0;10.0;100.0}
## Заключение
Проект демонстрирует комплексное применение принципов ООП в Java. Реализована сложная система взаимосвязанных классов с четким разделением ответственности, организацией кода в пакеты и соблюдением лучших практик разработки. Все задания выполнены в соответствии с требованиями, с использованием инкапсуляции, наследования, полиморфизма и абстракции.
`
