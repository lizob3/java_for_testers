package lisakeyy.geometry;

import lisakeyy.geometry.figures.Triangle;

public class Geometry {

    public static void main(String[] args) {
        Triangle.printTriangleArea(new Triangle(3.0, 4.0, 5.0));
        Triangle.printTrianglePerimeter(new Triangle(3.0, 4.0, 5.0));
    }
}
