import java.io.File;

public class Hello {
    public static void main() {
        try {
            var z = calculate();
            System.out.println(z);
        } catch (ArithmeticException exeption) {
            exeption.printStackTrace();
        }


        /*System.out.println(2 + 2);
        System.out.println(2 * 2);
        System.out.println(2 - 2);
        System.out.println(2 / 2);
        System.out.println((2 + 2) * 2);
        System.out.println("Hello " + "world");

        System.out.println("2 + 2 = " + 2 + 2);
        System.out.println("2 + 2 = " + (2 + 2));
        System.out.println(2 + 2 + " = 4");


        var configFile = new File("sandbox/build.gradle.kts");
        System.out.println(configFile.getAbsoluteFile());
        System.out.println(configFile.exists());*/
    }

    private static int calculate() {
        var x = 1;
        var y = 1;
        var z = devide(x, y);
        return z;
    }

    private static int devide(int x, int y) {
        var z = x / y;
        return z;
    }
}