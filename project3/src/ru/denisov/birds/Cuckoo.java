package ru.denisov.birds;

import java.util.concurrent.ThreadLocalRandom;

public class Cuckoo extends Bird { //куку рандомно от 1 до 10
    public Cuckoo() {
        super("Cuckoo");
    }

    @Override
    public void sing() {
        int times = ThreadLocalRandom.current().nextInt(1, 11);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            if (i > 0) sb.append(" ");
            sb.append("ку-ку");
        }
        System.out.println(sb.toString());
    }
}