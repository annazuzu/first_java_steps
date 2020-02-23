package ru.stqa.pft.sandbox;

public class Equality {
    public static void main(String[] args) {
        String s1 = "firefox 2.0";
//        String s2 = new String(s1);
//        String s2 = "firefox";
//        String s2 = "fire" + "fox";
        String s2 = "firefox " + Math.sqrt(4.0); //компилятор делает вычисления на этапе создания объекта, поэтому будут
        // заведомо создаваться два разных объекта => метод equals(), сравнивающий значения двух объектов, выполнит сравнение
        // с результатом "true", а "==" опять наткнется на два разных объекта и сравление будет ложным.

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

    }
}
