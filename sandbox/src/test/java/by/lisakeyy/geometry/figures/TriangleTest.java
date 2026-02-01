package by.lisakeyy.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(12.0, new Triangle(3.0, 4.0, 5.0).perimeter());
    }

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(6.0, new Triangle(3.0, 4.0, 5.0).area());
    }

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            new Triangle (-3.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void cannotBreakInequalityRule() {
        try {
            new Triangle (3.0, 1.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }
}
