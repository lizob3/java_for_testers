import java.io.File;

public class Hello {
    public static void main() {
        System.out.println("Hello world!");
        System.out.println(2 + 2);
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
        System.out.println(configFile.exists());
    }
}