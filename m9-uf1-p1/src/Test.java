import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String cadena = "jiins 0000000000000000000000000000000045337ab528ab456381fd0fe311633a6b";
        String[] array = cadena.split(" ");
        System.out.println(array[1]);
    }
}
