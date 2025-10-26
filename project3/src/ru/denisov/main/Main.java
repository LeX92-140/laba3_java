package ru.denisov.main;

import ru.denisov.geometry.*;
import ru.denisov.person.Name;
import ru.denisov.transport.*;
import ru.denisov.birds.*;
import ru.denisov.math.Fraction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nЗадание 3 - Трехмерная точка");
        Point3D p3d = new Point3D(1, 2, 3);
        System.out.println("3D точка - " + p3d);
        System.out.println("Клонирование 3D точки - " + p3d.clone());

        System.out.println("\nЗадание 5 (9.Городим), 1 (10.Дороги), 2 (5.Маршрут), 3 (3.Двусторонняя дорога)");
        City A = new TwoWayCity("A");
        City B = new City("B");
        City C = new City("C");
        City D = new TwoWayCity("D");
        City E = new City("E");
        City F = new City("F");

        A.addConnection(B, 1);
        A.addConnection(C, 1);
        D.addConnection(E, 1);
        D.addConnection(B, 1);

        B.addConnection(C, 1);
        E.addConnection(C, 1);

        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        System.out.println(E);
        System.out.println(F);

        try {
            A.addConnection(B, 5); //попробуем добавить существующую ожидаем исключение
        } catch (Exception ex) {
            System.out.println("Ожидаемая ошибка при дубликате дороги - " + ex.getMessage());
        }

        Route routeFD = new Route(F, D);
        System.out.println("Маршрут F - D: " + routeFD);

        F.addConnection(A, 1);
        Route route = new Route(F, D);
        System.out.println("Маршрут F - D (после добавления F-A): " + route);
        City[] path = route.getPath();
        System.out.print("Путь как массив: ");
        if (path.length == 0) {
            System.out.println("(пути нет)");
        } else {
            for (City c : path) System.out.print(c.getName() + " ");
            System.out.println();
        }

        System.out.println("\nЗадание 6 - 5)Сравнение городов");
        City X1 = new City("X1");
        City X2 = new City("X2");
        City Z = new City("Z");
        X1.addConnection(Z, 10);
        X2.addConnection(Z, 10);
        System.out.println("Дорога X1 равна дороге X2 - " + X1.equals(X2));

        System.out.println("\nЗадание 4 - 3)Птицы");
        Bird sparrow = new Sparrow();
        Bird cuckoo = new Cuckoo();
        Bird parrot = new Parrot("Привет всем я глупый");
        sparrow.sing();
        cuckoo.sing();
        parrot.sing();

        System.out.println("\nЗадание 7 - 3)Ввод возведения в степень");
        System.out.print("Введите X целое - ");
        String xs = scanner.nextLine().trim();
        System.out.print("Введите Y целое - ");
        String ys = scanner.nextLine().trim();
        try {
            int X = Integer.parseInt(xs);
            int Y = Integer.parseInt(ys);
            double pow = Math.pow(X, Y);
            System.out.println(X + "^" + Y + " = " + pow);
        } catch (NumberFormatException nfe) {
            System.out.println("Ошибка - введены не целые числа");
        }

        scanner.close();

        System.out.println("\nПрограмма завершена");
    }
}