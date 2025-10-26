package ru.denisov.birds;

public class Sparrow extends Bird { //Воробей поёт чырык
    public Sparrow() {
        super("Sparrow");
    }

    @Override
    public void sing() {
        System.out.println("чырык");
    }
}