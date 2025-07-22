import java.io.*;
import java.net.Socket;

public class WelcomeClient {
    private Socket socket;
    private OutputStream out;
    private InputStream in;

    public void connecta(String ipAddress, int port) {
        try {
            socket = new Socket(ipAddress, port);
            System.out.println("Establerts connexio amb el servidor");
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String llegeixMissatge() {
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String missatge = null;

        try {
            missatge = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return missatge;
    }
}
