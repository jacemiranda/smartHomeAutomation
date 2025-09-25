package smartHome;

public class IncreaseTempCommand implements Command {
    private final Thermostat thermostat;
    private final int delta;
    public IncreaseTempCommand(Thermostat t, int delta) { this.thermostat = t; this.delta = delta; }
    @Override public void execute() {
        System.out.println("[CMD] IncreaseTempCommand.execute()");
        thermostat.increase(delta);
    }
    @Override public void undo() { thermostat.decrease(delta); }
}