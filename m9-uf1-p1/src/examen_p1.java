import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class examen_p1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "m9-uf1-pvp-part1_usuaris_hash.txt";
        System.out.println("Indica la contrasenya:");
        String password = sc.nextLine();

        boolean isValid = validateCredentials(password, file);
        if (isValid) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is NOT valid");
        }
    }

    public static boolean validateCredentials(String password, String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // separar l'usuari de les altres dades
                String[] user = line.split(" -> ");
                String username = user[0];
                String[] parts = user[1].split(" : ");
                String saltBase64 = parts[0];
                String hashBase64 = parts[1];

                // descodificar salt i hash de base64
                byte[] salt = Base64.getDecoder().decode(saltBase64);
                byte[] storedHash = Base64.getDecoder().decode(hashBase64);

                //afegir salt a la contrasenya introduida
                byte[] passwordWithSalt = new byte[salt.length + password.length()];
                System.arraycopy(salt, 0, passwordWithSalt, 0, salt.length);
                System.arraycopy(password.getBytes(StandardCharsets.UTF_8), 0, passwordWithSalt, salt.length, password.length());

                // calcular el hash MD5 de la combinació salt + contrasenya
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] calculatedHash = messageDigest.digest(passwordWithSalt);


                if (MessageDigest.isEqual(calculatedHash, storedHash)) {
                    return true;
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
