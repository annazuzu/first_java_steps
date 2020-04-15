package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class SquareTests {
    @Test //аннотация для тестового фрейморка. По этим пометкам фреймворк находит нужные методы и он является сигналом,
    // что этот метод нужно запустить и он является тестом.

    public void testArea(){
        Square s = new Square(5);
        assert s.area() == 25;
    }
//  1. Проверить египетский треугольник с соотношением сторон 3:4:5.
//  2. Проверить, если у одной точки отрицательное значение (5, 12, 13).
//  3. Проверить, если у точек дробные значения (1.33, 1.56, 2.05)

    @Test
    public void testDistance1() {
        Point p1 = new Point(3,4);
        Point p2 = new Point(7,7);
        assert p2.distance(p1) == 5;
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(-6,-3);
        Point p2 = new Point(6,2);
        assert p2.distance(p1) == 13;
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(1.33,1.56);
        assert Math.abs(p2.distance(p1) - 2.05) < 0.00000000000001;

//        assert BigDecimal.valueOf(p2.distance(p1)).setScale(2,BigDecimal.ROUND_HALF_EVEN).doubleValue() == 2.05; тест
//        падает при значении "== 2.05". хотя при ">= 2.05" - проходит. Значит "приемка"
//        результата идет таки по полному значению double, несмотря на округление.
    }


}
