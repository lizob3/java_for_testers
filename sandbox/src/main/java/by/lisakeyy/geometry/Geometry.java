package by.lisakeyy.geometry;

import by.lisakeyy.geometry.figures.Rectangle;
import by.lisakeyy.geometry.figures.Square;
import by.lisakeyy.geometry.figures.Triangle;

public class Geometry {
    static void main() {
        Square.printSquareArea (new Square (7.0));
        Square.printSquareArea (new Square (5.0));
        Square.printSquareArea (new Square (3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        Triangle.printTrianglePerimeter(new Triangle(8.0, 7.0, 3.0));
        Triangle.printTriangleArea(new Triangle(8.0, 7.0, 3.0));
    }
}