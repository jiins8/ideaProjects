import java.io.*;
import java.net.*;
import java.util.concurrent.locks.*;

public class WelcomeServer {
    private static int totalSum = 0;
    private static String numberSequence = "0";
    private static final ReentrantLock lock = new ReentrantLock();

    public static void handleClient(Socket socket) {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String clientNumberStr = reader.readLine();
            int clientNumber = Integer.parseInt(clientNumberStr);

            lock.lock();
            try {
                totalSum += clientNumber;
                numberSequence += "+" + clientNumber;
                String response = numberSequence + "=" + totalSum;
                writer.println(response);
            } finally {
                lock.unlock();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Socket closing exception: " + ex.getMessage());
            }
        }
    }
}
