import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class examen_p2_2 {

    public static void main(String[] args) {
        try {
            // Carregar la clau privada del receptor
            PrivateKey privateKeyReceptor = getPrivateKey("Receptor-clauPrivada.key");

            // Llegir la clau precompartida encriptada
            byte[] encryptedSharedKey = Files.readAllBytes(Paths.get("ClauPreCompartidaXifrada.txt"));

            // Desxifrar la clau precompartida
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKeyReceptor);
            byte[] decryptedSharedKey = cipher.doFinal(encryptedSharedKey);

            // Convertir la clau desxifrada a string (opcional, depenent de com s'ha encriptat la clau originalment)
            String sharedKey = new String(decryptedSharedKey);

            // Imprimir la clau desxifrada
            System.out.println("La clau precompartida desxifrada és: " + sharedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readKeyFromFile(String filename) throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(filename)));
        return key;
    }

    public static PrivateKey getPrivateKey(String filename) throws Exception {
        String key = readKeyFromFile(filename);
        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
