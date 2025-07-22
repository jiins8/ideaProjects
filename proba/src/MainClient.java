public class MainClient {
    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        int port = 65432;

        for (int i = 1; i <= 10; i++) {
            WelcomeClient client = new WelcomeClient(hostname, port, i);
            client.execute();
        }
    }
}
