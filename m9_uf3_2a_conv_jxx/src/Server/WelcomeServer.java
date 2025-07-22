package Server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WelcomeServer {
    private static int sumTotal = 0;
    private static String trenDeNumeros = "0";
    private static final Lock lock = new ReentrantLock();

    public static void clientHanlder(Socket socket) {
        try (InputStream in = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             OutputStream out = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(out, true)) {

            String numeroClientStr = reader.readLine();
            int numeroClient = Integer.parseInt(numeroClientStr);

            lock.lock();

            try {
                sumTotal = sumTotal + numeroClient;
                trenDeNumeros = trenDeNumeros + " + " + numeroClient;
                String response = trenDeNumeros + " = " + sumTotal;
                writer.println(response);
            } finally {
                lock.unlock();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

