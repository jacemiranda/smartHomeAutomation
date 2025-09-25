package smartHome;

public class Light implements Device {
    private int brightness = 50;
    private final String name;

    public Light(String name) { this.name = name; }

    public void on() {
        System.out.println("[Light] " + name + " -> ON (brightness " + brightness + "%)");
    }

    public void off() {
        System.out.println("[Light] " + name + " -> OFF");
    }

    public void setBrightness(int value) {
        brightness = Math.max(0, Math.min(100, value));
        System.out.println("[Light] " + name + " -> Brightness set to " + brightness + "%");
    }

    @Override
    public String getName() { return name; }
}