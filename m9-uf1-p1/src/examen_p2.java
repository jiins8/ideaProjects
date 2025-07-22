import javax.crypto.Cipher;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class examen_p2 {
    public static void main(String[] args) {
        String receptorClauPrivada = "Receptor-clauPrivada.key"; // Path to the private key file
        String encryptedFile = "ClauPreCompartidaXifrada.txt"; // Path to the encrypted file
        String decryptedFile = "ClauPreCompartidaDesxifrada.txt"; // Path to the decrypted file

        try {
            PrivateKey privateKey = loadPrivateKey(receptorClauPrivada);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] encryptedBytes = Files.readAllBytes(Paths.get(encryptedFile));

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            try (FileOutputStream fos = new FileOutputStream(decryptedFile)) {
                fos.write(decryptedBytes);
            }

            System.out.println("File decrypted successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static PrivateKey loadPrivateKey(String fileName) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(fileName));
        keyBytes = Base64.getDecoder().decode(keyBytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}

