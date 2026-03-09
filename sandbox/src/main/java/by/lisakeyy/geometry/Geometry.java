package by.lisakeyy.geometry;

import by.lisakeyy.geometry.figures.Rectangle;
import by.lisakeyy.geometry.figures.Square;
import by.lisakeyy.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    static void main() {
        var squares = List.of(new Square (7.0), new Square (5.0), new Square (3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }

        squares.forEach(Square::printSquareArea);

//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7.0, 9.0);

//        Triangle.printTrianglePerimeter(new Triangle(8.0, 7.0, 3.0));
//        Triangle.printTriangleArea(new Triangle(8.0, 7.0, 3.0));
    }
}