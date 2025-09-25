package smartHome;

import java.util.HashMap;
import java.util.Map;

public class CentralHub {
    private final Map<String, Command> slots = new HashMap<>();

    public void bind(String key, Command command) {
        slots.put(key, command);
    }

    public void press(String key) {
        Command c = slots.get(key);
        if (c != null) {
            c.execute();
        } else {
            System.out.println("[Hub] No command bound to: " + key);
        }
    }

    public void pressUndo(String key) {
        Command c = slots.get(key);
        if (c != null) {
            c.undo();
        } else {
            System.out.println("[Hub] No command bound to: " + key);
        }
    }
}