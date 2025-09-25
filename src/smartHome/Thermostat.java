package smartHome;

public class Thermostat implements Device {
    private int temperature = 24;
    private final String name;

    public Thermostat(String name) { this.name = name; }

    public void increase(int delta) {
        temperature += delta;
        System.out.println("[Thermostat] " + name + " -> Temperature " + temperature + "°C");
    }

    public void decrease(int delta) {
        temperature -= delta;
        System.out.println("[Thermostat] " + name + " -> Temperature " + temperature + "°C");
    }

    public int getTemperature() { return temperature; }

    @Override
    public String getName() { return name; }
}