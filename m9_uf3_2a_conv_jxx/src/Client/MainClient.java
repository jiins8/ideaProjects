package Client;

public class MainClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;

        for (int i = 1; i <= 10; i++) {
            WelcomeClient client = new WelcomeClient(host, port, i);
            client.run();
        }

    }
}