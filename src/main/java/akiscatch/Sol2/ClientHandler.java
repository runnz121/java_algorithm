package akiscatch.Sol2;


import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Map<String, IoTDevice> devices;

    public ClientHandler(Socket socket, Map<String, IoTDevice> devices) {
        this.socket = socket;
        this.devices = devices;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer  = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String input;
            while ((input = reader.readLine()) != null) {
                if ("EXIT".equals(input)) {
                    break;
                }

                // 분리
                String[] parts = input.split(" ", 2);
                String deviceName = parts[0];
                String cmdBody    = parts.length > 1 ? parts[1] : "";

                IoTDevice device = devices.get(deviceName);
                if (device == null) {
                    writer.println(ResponseTemplate.deviceNotFound(deviceName));
                    continue;
                }

                CommandResult result = device.processCommand(cmdBody);
                switch (result) {
                    case SUCCESS_TURNON:
                    case SUCCESS_TURNOFF:
                        writer.println(ResponseTemplate.devicePowerChanged(deviceName, device.isOn()));
                        break;
                    case SUCCESS_COMMAND:
                        writer.println(ResponseTemplate.success(input));
                        break;
                    case DEVICE_OFF:
                        writer.println(ResponseTemplate.deviceOff(input));
                        break;
                    case INVALID:
                    default:
                        writer.println(ResponseTemplate.invalidCommand(input));
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("클라이언트 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
