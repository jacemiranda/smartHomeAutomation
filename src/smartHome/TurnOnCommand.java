package smartHome;

public class TurnOnCommand implements Command {
    private final Light light;
    public TurnOnCommand(Light light) { this.light = light; }
    @Override public void execute() {
        System.out.println("[CMD] TurnOnCommand.execute()");
        light.on();
    }
    @Override public void undo() { light.off(); }
}