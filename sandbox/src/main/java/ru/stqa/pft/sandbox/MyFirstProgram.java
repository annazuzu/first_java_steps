package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " - " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " - " + r.area());

        Point p1 = new Point(2,4);

        Point p2 = new Point(5,6);

        System.out.println("Расстояние от точки х(" + p1.x + "," + p1.y + ") до точки у(" + p2.x + "," + p2.y +") = " +
                p2.distance(p1));

    }

}

