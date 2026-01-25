public class Geometry {
    static void main() {
        printSquareArea(7.0);
        printSquareArea(5.0);
        printSquareArea(3.0);

        printRectangleArea(3.0, 5.0);
        printRectangleArea(7.0, 9.0);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Rectangle's area with sides " + a + " and " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {
        System.out.println("Square's area with a side " + side + " = " + sqareArea(side));
    }

    private static double sqareArea(double a) {
        return a * a;
    }
}
