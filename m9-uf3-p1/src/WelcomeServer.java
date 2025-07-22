import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WelcomeServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private int PORT = 6000;
    private final String WELCOME_MESSAGE = "Benvingut al servidor";
    private OutputStream out;
    private InputStream in;

    public void inicaServei() {
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            this.gestionaNovaConnexio(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gestionaNovaConnexio(Socket socket) {
        try {
            out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(WELCOME_MESSAGE);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void tancaConnexio() {
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
