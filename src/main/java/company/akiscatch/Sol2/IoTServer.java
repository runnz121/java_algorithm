package company.akiscatch.Sol2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;

public class IoTServer {
    private final int port;
    private final Map<String, IoTDevice> devices;
    private ServerSocket serverSocket;
    private Thread serverThread;

    public IoTServer(int port, Map<String, IoTDevice> devices) {
        this.port = port;
        this.devices = devices;
    }

    public void startServer() {
        serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(1123);
                serverSocket.setSoTimeout(5_000);
                while (true) {
                    try {
                        Socket client = serverSocket.accept();
                        new Thread(new ClientHandler(client, devices)).start();
                    } catch (SocketTimeoutException toe) {
                        // 5초 동안 접속 없으면 종료
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("서버 오류: " + e.getMessage());
            } finally {
                stopServer();
            }
        }, "IoT-Server-Thread");
        serverThread.start();
    }

    public void stopServer() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {}
        }
    }

    public Thread getServerThread() {
        return serverThread;
    }
}
