package smartHome;

public class OpenCurtainCommand implements Command {
    private final CurtainAdapter curtain;
    public OpenCurtainCommand(CurtainAdapter c) { this.curtain = c; }
    @Override public void execute() {
        System.out.println("[CMD] OpenCurtainCommand.execute()");
        curtain.open();
    }
    @Override public void undo() { curtain.close(); }
}