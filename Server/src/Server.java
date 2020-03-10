import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    private final static Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {

            LOGGER.info("Server started");

            while (true) {
                Socket socket = server.accept();

                new Thread(() -> {
                    try {

                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        String request = reader.readLine();
                        LOGGER.info("Received Request From Client");

                        String response = "Weather is : " + request.length() + " C";
                        Thread.sleep(3000);

                        LOGGER.info("Send Response To Client");
                        bufferedWriter.write(response);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        LOGGER.info("Close Connection");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
