package smartHome;

public class DecreaseTempCommand implements Command {
    private final Thermostat thermostat;
    private final int delta;
    public DecreaseTempCommand(Thermostat t, int delta) { this.thermostat = t; this.delta = delta; }
    @Override public void execute() {
        System.out.println("[CMD] DecreaseTempCommand.execute()");
        thermostat.decrease(delta);
    }
    @Override public void undo() { thermostat.increase(delta); }
}