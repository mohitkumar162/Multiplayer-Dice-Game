import java.io.*;
import java.net.*;

public class GameServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Server started and ready! 🟢");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected! 🔗");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("CLIENT: " + msg);
            }
            socket.close();
            System.out.println("Client disconnected! ❌");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
