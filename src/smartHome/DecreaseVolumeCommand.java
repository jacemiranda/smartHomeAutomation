package smartHome;

public class DecreaseVolumeCommand implements Command {
    private final MusicPlayer player;
    private final int delta;
    public DecreaseVolumeCommand(MusicPlayer p, int delta) { this.player = p; this.delta = delta; }
    @Override public void execute() {
        System.out.println("[CMD] DecreaseVolumeCommand.execute()");
        player.decreaseVolume(delta);
    }
    @Override public void undo() { player.increaseVolume(delta); }
}