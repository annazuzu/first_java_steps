package ru.stqa.pft.sandbox;

public class Point {
    double x;
    double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;

    }

    public double distance(Point px) {
        double deltax;
        double deltay;
        deltax = px.x - x;
        deltay = px.y - y;

        return Math.sqrt(Math.pow(deltax,2) + Math.pow(deltay,2));
    }


}
