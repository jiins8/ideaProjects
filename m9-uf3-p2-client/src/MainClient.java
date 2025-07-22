import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.connectaAServidor("127.0.0.1", 65000);
            String missatge = client.llegeixMissatgeDelServidor();
            System.out.println("El servidor diu: " + missatge);
        } catch (IOException e) {
            System.out.println("Error connectant o llegint al servidor");
        }
        client.desconnecta();
    }

}
