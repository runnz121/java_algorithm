package company.akiscatch.Sol2;


public class Light extends IoTDevice {
    private int brightness;

    public Light(String name) {
        super(name);
        this.brightness = 50;
    }

    /**
     * Executes a custom command for the Light device.
     * Supported command: "BRIGHT <0–100>"
     */
    @Override
    protected CommandResult executeCustomCommand(String command) {
        if (command.startsWith("BRIGHT")) {
            String[] parts = command.split(" ", 2);
            if (parts.length != 2) return CommandResult.INVALID;
            try {
                int level = Integer.parseInt(parts[1]);
                if (level < 0 || level > 100) return CommandResult.INVALID;
                brightness = level;
                return CommandResult.SUCCESS_COMMAND;
            } catch (NumberFormatException e) {
                return CommandResult.INVALID;
            }
        }
        return CommandResult.INVALID;
    }
}
