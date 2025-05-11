package akiscatch.Sol2;


public class ResponseTemplate {
    public static String devicePowerChanged(String deviceName, boolean isOn) {
        return deviceName + " is now " + (isOn ? "ON" : "OFF") + ".";
    }

    public static String success(String fullCommand) {
        return "Executed command: " + fullCommand;
    }

    public static String deviceNotFound(String deviceName) {
        return "Device " + deviceName + " not found.";
    }

    public static String deviceOff(String fullCommand) {
        return "Fail: [" + fullCommand + "] Device is OFF.";
    }

    public static String invalidCommand(String fullCommand) {
        return "Fail: [" + fullCommand + "] Invalid Command.";
    }
}
