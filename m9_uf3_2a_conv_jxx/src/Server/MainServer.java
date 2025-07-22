package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static final int PORT = 6000;
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escoltant el port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nou client conectat des de:" + socket.getRemoteSocketAddress());
                new Thread(() -> WelcomeServer.clientHanlder(socket)).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

