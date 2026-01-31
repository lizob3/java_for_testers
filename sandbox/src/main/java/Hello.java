public class Hello {
    public static void main() {
        var x = 1;
        var y = 0;
        if (y == 0) {
            System.out.println("Devision by zero is not allowed");
        } else {
            var z = devide(x, y);
            System.out.println("Hello world!");
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

    private static int devide(int x, int y) {
        var z = x / y;
        return z;
    }
}