import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class examen_p1_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix la paraula: ");
        String inputPassword = scanner.nextLine();

        String filePath = "m9-uf1-pvp-part1_usuaris_hash.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Separar la línia en usuari, salt i hash
                String[] parts = line.split(" -> ");
                String[] credentials = parts[1].split(" : ");
                String saltBase64 = credentials[0].trim();
                String hashBase64 = credentials[1].trim();

                // Decodificar el salt i el hash en Base64
                byte[] salt = Base64.getDecoder().decode(saltBase64);
                byte[] hash = Base64.getDecoder().decode(hashBase64);

                // Concatenar el salt amb la paraula introduïda i calcular el hash MD5
                byte[] inputPasswordBytes = inputPassword.getBytes();
                byte[] saltedPassword = new byte[salt.length + inputPasswordBytes.length];
                System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
                System.arraycopy(inputPasswordBytes, 0, saltedPassword, salt.length, inputPasswordBytes.length);

                byte[] calculatedHash = calculateMD5(saltedPassword);

                // Comparar el hash calculat amb el hash guardat en el fitxer
                if (MessageDigest.isEqual(calculatedHash, hash)) {
                    System.out.println("La paraula correspon a la contrasenya de l'usuari: " + parts[0]);
                    return;
                }
            }
            System.out.println("La paraula no correspon a cap contrasenya guardada.");
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static byte[] calculateMD5(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MaD5");
        return md.digest(input);
    }
}
