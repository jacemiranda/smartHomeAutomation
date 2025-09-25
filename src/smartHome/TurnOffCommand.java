package smartHome;

public class TurnOffCommand implements Command {
    private final Light light;
    public TurnOffCommand(Light light) { this.light = light; }
    @Override public void execute() {
        System.out.println("[CMD] TurnOffCommand.execute()");
        light.off();
    }
    @Override public void undo() { light.on(); }
}