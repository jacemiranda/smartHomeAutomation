package smartHome;

public class IncreaseVolumeCommand implements Command {
    private final MusicPlayer player;
    private final int delta;
    public IncreaseVolumeCommand(MusicPlayer p, int delta) { this.player = p; this.delta = delta; }
    @Override public void execute() {
        System.out.println("[CMD] IncreaseVolumeCommand.execute()");
        player.increaseVolume(delta);
    }
    @Override public void undo() { player.decreaseVolume(delta); }
}