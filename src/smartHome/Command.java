package smartHome;

public interface Command {
    void execute();
    default void undo() {}
}