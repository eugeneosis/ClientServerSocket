import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000)) {

            System.out.println("Server started!");

            while (true) {
                Socket socket = server.accept();

                new Thread(() -> {
                    try {

                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        String request = reader.readLine();
                        System.out.println("Request : " + request);
                        String response = "Weather is : " + request.length() + " C";
                        Thread.sleep(3000);
                        System.out.println("Response : " + response);
                        bufferedWriter.write(response);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (Exception e) {
                        System.out.println("?");
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
