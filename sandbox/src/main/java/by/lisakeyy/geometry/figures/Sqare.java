package by.lisakeyy.geometry.figures;

public class Sqare {
    public static void printSquareArea(double side) {
        String text = String.format("Square's area with a side %f = %f", side, sqareArea(side));
        System.out.println(text);
    }

    private static double sqareArea(double a) {
        return a * a;
    }
}