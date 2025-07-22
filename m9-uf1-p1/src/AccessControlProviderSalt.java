import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AccessControlProviderSalt {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escull tipus d'algoritme");
        System.out.println(tipusFuncionsHash());
        String algoritme = sc.next();
        System.out.println("Usuari");
        String usuari = sc.next();
        System.out.println("Password");
        String password = sc.next();
        String salt = "qwertyuiop!@#$%^&*()zxcvbnm,.";
        password = password+salt;


        try {
            if (!tipusFuncionsHash().contains(algoritme)) {
                throw new Exception();
            }
            System.out.println("Trobat");
            System.out.println(usuari);
            String hash = encriptacio(algoritme, password);
            System.out.println(hash);


            saveCredentials(usuari, hash);
            String cadena = readTxt();
            String[] array = cadena.split(" ");
            validateCredentials(hash, array[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static List<String> tipusFuncionsHash() {
        Provider[] availableProviders = Security.getProviders();
        List<String> algoritmesDeTipusMessageDigest = null;
        List<String> funcionsHash = new ArrayList<>();
        for (Provider p : availableProviders) {
            Set<Provider.Service> services = p.getServices();
            algoritmesDeTipusMessageDigest = services.stream()
                    .filter(s -> s.getType().equals(MessageDigest.class.getSimpleName()))
                    .map(s -> s.getAlgorithm())
                    .sorted()
                    .collect(Collectors.toList());
            if (!algoritmesDeTipusMessageDigest.isEmpty()) {
                funcionsHash.addAll(algoritmesDeTipusMessageDigest);
            }
        }
        return funcionsHash;
    }

    private static String encriptacio(String algoritme, String cadena) {
        String hashString = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algoritme);
            messageDigest.reset();
            messageDigest.update(cadena.getBytes(StandardCharsets.UTF_8));
            byte[] hash = messageDigest.digest();
            hashString = String.format("%064x", new BigInteger(1, hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashString;
    }

    private static void saveCredentials(String usuari, String password) {
        try {
            File file = new File("C://Users//jinsh//IdeaProjects//m9-uf1-p1/test.txt");
            if (!file.exists()){
                file.createNewFile();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(usuari);
            bufferedWriter.newLine();
            bufferedWriter.write(password + "\n");

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readTxt() {
        String text = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                text += line + " ";
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.trim();
    }
    private static void validateCredentials(String password, String password2){

        System.out.println(MessageDigest.isEqual(password.getBytes(), password2.getBytes()));
        System.out.println(password.getBytes());
        System.out.println(password2.getBytes());
    }
}