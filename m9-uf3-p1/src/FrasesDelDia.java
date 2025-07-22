import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class FrasesDelDia {

    private static final String[] FRASES = {
            "Never give someone the opportunity to waste your time twice",
            "Dicipline + Focus + Action = Success",
            "Push yourself because no one else is going to do it for you",
            "Take the risk or lose the chance",
            "If you want it, work for it"
    };

    public static void main(String[] args) {
        int port = 8000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is waiting to connect to the port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress());
                enviarFrase(clientSocket);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarFrase(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        Random random = new Random();
        int index = random.nextInt(FRASES.length);
        out.println(FRASES[index]);
        out.close();
    }
}