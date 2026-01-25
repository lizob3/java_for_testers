package by.lisakeyy.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Triangle's area with sides %f, %f, %f = %f", t.a, t.b, t.c, t.perimeter());
        System.out.println(text);
    }

    public static void printTriangleArea(Triangle t) {
        String text = String.format("Triangle's area with sides %f, %f, %f = %f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public double area() {
        var semip = perimeter()/2;
        return sqrt(semip * (semip - a) * (semip - b) * (semip - c));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

}