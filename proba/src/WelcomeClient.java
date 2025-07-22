import java.io.*;
import java.net.*;

public class WelcomeClient {
    private String hostname;
    private int port;
    private int numberToSend;

    public WelcomeClient(String hostname, int port, int numberToSend) {
        this.hostname = hostname;
        this.port = port;
        this.numberToSend = numberToSend;
    }

    public void execute() {
        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            writer.println(numberToSend);
            String response = reader.readLine();

            System.out.println("Client" + numberToSend + ": " + response);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
