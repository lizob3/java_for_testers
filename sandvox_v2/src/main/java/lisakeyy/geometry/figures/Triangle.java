package lisakeyy.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Triangle's side should be non-negative");
        }

        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side3 + side2 <= side1) {
            throw new IllegalArgumentException("Triangle inequality rule is broken");
        }
    }

    public double area() {
        double semip = this.perimeter()/2;
        return sqrt(semip*(semip-this.side1)*(semip-this.side2)*(semip-this.side3));
    }

    public double perimeter() {
        return this.side1+this.side2+this.side3;
    }

    public static void printTriangleArea (Triangle triangle) {
        var text = String.format("Triangle's square with sides %f, %f, %f = %f", triangle.side1,
                triangle.side2, triangle.side3, triangle.area());
        System.out.println(text);
    }

    public static void printTrianglePerimeter (Triangle triangle) {
        var text = String.format("Triangle's perimeter with sides %f, %f, %f = %f", triangle.side1,
                triangle.side2, triangle.side3, triangle.perimeter());
        System.out.println(text);
    }
}
