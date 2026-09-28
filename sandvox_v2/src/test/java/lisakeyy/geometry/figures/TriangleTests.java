package lisakeyy.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var triangle = new Triangle(3.0, 4.0, 5.0);
        double result = triangle.area();
        Assertions.assertEquals(6.0, result);

    }

    @Test
    void canCalculatePerimeter() {
        var triangle = new Triangle(3.0, 4.0, 5.0);
        double result = triangle.perimeter();
        Assertions.assertEquals(12.0, result);

    }

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            var triangle = new Triangle(-3.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void cannotBreakInequalityRule() {
        try {
            var triangle = new Triangle(1.0, 2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }
}
