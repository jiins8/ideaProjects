import java.io.*;
import java.net.*;

public class MainServer {
    public static void main(String[] args) {
        int port = 65432;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected from " + socket.getRemoteSocketAddress());

                new Thread(() -> WelcomeServer.handleClient(socket)).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
