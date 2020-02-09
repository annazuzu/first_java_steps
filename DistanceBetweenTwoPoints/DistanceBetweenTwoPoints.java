

public class DistanceBetweenTwoPoints {
    public static class Point {
        double x;
        double y;
    }

    public static double distance(Point pa, Point pb) {
        double deltax;
        double deltay;
        deltax = pb.x - pa.x;
        deltay = pb.y - pa.y;

        return Math.sqrt(Math.pow(deltax,2) + Math.pow(deltay,2));
    }


    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 2;
        p1.y = 4;
        Point p2 = new Point();
        p2.x = 5;
        p2.y = 6;

//        double c = distance(p1,p2);

        System.out.println(distance(p1,p2));

    }
}
