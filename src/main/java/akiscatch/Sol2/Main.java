package akiscatch.Sol2;


import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int port = 5000;
        Map<String, IoTDevice> devices = new HashMap<>();
//        devices.put("airconditioner", new AirConditioner("airconditioner"));
        devices.put("light",          new Light("light"));
//        devices.put("washingmachine", new WashingMachine("washingmachine"));

        // 서버 시작
        IoTServer server = new IoTServer(port, devices);
        server.startServer();


        // 서버 스레드가 종료될 때까지 대기
        server.getServerThread().join();

    }
}
