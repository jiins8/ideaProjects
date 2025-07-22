package Client;

import java.io.*;
import java.net.Socket;

public class WelcomeClient {
    private String host;
    private int port;
    private int numeroAEnviar;

    public WelcomeClient(String host, int port, int numeroAEnviar) {
        this.host = host;
        this.port = port;
        this.numeroAEnviar = numeroAEnviar;
    }

    public void run() {
        try (Socket socket = new Socket(host, port)) {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            writer.println(numeroAEnviar);
            String response = reader.readLine();

            System.out.println("Client " + numeroAEnviar + ": " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
