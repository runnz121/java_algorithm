package company.akiscatch.Sol2;

public abstract class IoTDevice {

    private final String name;
    private boolean isOn;

    public IoTDevice(String name) {
        this.name = name;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public CommandResult processCommand(String command) {
        if ("ON".equals(command)) {
            turnOn();
            return CommandResult.SUCCESS_TURNON;
        }
        if ("OFF".equals(command)) {
            turnOff();
            return CommandResult.SUCCESS_TURNOFF;
        }
        if (!isOn) {
            return CommandResult.DEVICE_OFF;
        }
        return executeCustomCommand(command);
    }

    protected abstract CommandResult executeCustomCommand(String command);

    public String getName() {
        return name;
    }
}
