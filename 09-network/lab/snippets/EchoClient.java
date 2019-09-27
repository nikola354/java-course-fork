import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    private static final int SERVER_PORT = 4444;

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", SERVER_PORT);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); // autoflush on
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Scanner scanner = new Scanner(System.in);) {

            while (true) {
                String message = scanner.nextLine(); // read a line from the console
                
                if ("quit".equals(message)) {
                    break;
                }
                
                System.out.println("Sending message <" + message + "> to the server...");

                writer.println(message); // send the message to the server

                String reply = reader.readLine(); // read the response from the server
                System.out.println("The server replied <" + reply + ">");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
