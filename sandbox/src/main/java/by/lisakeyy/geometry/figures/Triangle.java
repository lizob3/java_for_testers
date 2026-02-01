package by.lisakeyy.geometry.figures;

import java.util.Objects;

import static java.lang.Math.sqrt;

public record Triangle (double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Triangle inequality rule is broken");
        }
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}