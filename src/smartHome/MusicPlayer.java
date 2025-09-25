package smartHome;

public class MusicPlayer implements Device {
    private int volume = 30;
    private final String name;

    public MusicPlayer(String name) { this.name = name; }

    public void play(String playlist) {
        System.out.println("[Player] " + name + " -> Playing playlist: " + playlist);
    }

    public void stop() {
        System.out.println("[Player] " + name + " -> Stopped");
    }

    public void increaseVolume(int delta) {
        volume = Math.min(100, volume + delta);
        System.out.println("[Player] " + name + " -> Volume " + volume + "%");
    }

    public void decreaseVolume(int delta) {
        volume = Math.max(0, volume - delta);
        System.out.println("[Player] " + name + " -> Volume " + volume + "%");
    }

    public int getVolume() { return volume; }

    @Override
    public String getName() { return name; }
}