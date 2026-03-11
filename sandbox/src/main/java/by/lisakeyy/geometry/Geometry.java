package by.lisakeyy.geometry;

import by.lisakeyy.geometry.figures.Rectangle;
import by.lisakeyy.geometry.figures.Square;
import by.lisakeyy.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    static void main() {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);

        Consumer<Square> print = square -> {
            Square.printSquareArea(square);
            Square.printSquarePerimeter(square);
        };

        squares.peek(Square::printSquareArea).forEach(Square::printSquarePerimeter);


//        Triangle.printTrianglePerimeter(new Triangle(8.0, 7.0, 3.0));
//        Triangle.printTriangleArea(new Triangle(8.0, 7.0, 3.0));
    }
}