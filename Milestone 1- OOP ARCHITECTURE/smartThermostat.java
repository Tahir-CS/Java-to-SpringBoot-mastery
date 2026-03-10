
public class smartThermostat extends SmartDevice {
    private double temperature = 22.0;

    public smartThermostat(String name) {
        super(name);
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
        System.out.println(deviceName + " temperature set to " + temp + "°C.");
    }

    @Override
    public void displayStatus() {
        String power = isEnabled ? "ON" : "OFF";
        System.out.println("[Thermostat] " + deviceName + " | Power: " + power + " | Temp: " + temperature + "°C");
    }
}
