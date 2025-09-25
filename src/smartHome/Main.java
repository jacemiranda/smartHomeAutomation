package smartHome;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light livingRoomLight = new Light("LivingRoomLight");
        Thermostat thermostat = new Thermostat("Thermostat");
        MusicPlayer player = new MusicPlayer("MusicPlayer");
        CurtainAdapter curtain = new CurtainAdapter("Curtain");

        CentralHub hub = new CentralHub();
        hub.bind("L1-ON",   new TurnOnCommand(livingRoomLight));
        hub.bind("L1-OFF",  new TurnOffCommand(livingRoomLight));
        hub.bind("TEMP-UP", new IncreaseTempCommand(thermostat, 1));
        hub.bind("TEMP-DOWN", new DecreaseTempCommand(thermostat, 1));
        hub.bind("VOL-UP",  new IncreaseVolumeCommand(player, 1));
        hub.bind("VOL-DOWN",new DecreaseVolumeCommand(player, 1));
        hub.bind("CURTAIN-OPEN", new OpenCurtainCommand(curtain));

        boolean running = true;
        while (running) {
            System.out.println("\n=== SMART HOME MAIN MENU ===");
            System.out.println("1. Control Light");
            System.out.println("2. Set Brightness");
            System.out.println("3. Control Thermostat");
            System.out.println("4. Control Music Player");
            System.out.println("5. Control Curtain");
            System.out.println("6. Show Status");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            switch (readInt(sc)) {
                case 1 -> lightMenu(sc, hub);
                case 2 -> setBrightnessMenu(sc, livingRoomLight);
                case 3 -> thermostatMenu(sc, hub, thermostat);
                case 4 -> musicMenu(sc, hub, player);
                case 5 -> curtainMenu(sc, hub);
                case 6 -> showStatus(livingRoomLight, thermostat, player);
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
        System.out.println("Exiting Smart Home. Goodbye!");
    }

    // ---------- Submenus ----------

    private static void lightMenu(Scanner sc, CentralHub hub) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Light Menu ---");
            System.out.println("1. Turn ON");
            System.out.println("2. Turn OFF");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            switch (readInt(sc)) {
                case 1 -> hub.press("L1-ON");
                case 2 -> hub.press("L1-OFF");
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void setBrightnessMenu(Scanner sc, Light light) {
        System.out.print("\nEnter brightness (0–100): ");
        int value = getIntInRange(sc, 0, 100);
        light.setBrightness(value);
    }

    private static void thermostatMenu(Scanner sc, CentralHub hub, Thermostat t) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Thermostat Menu ---");
            System.out.println("Current Temp: " + t.getTemperature() + "°C");
            System.out.println("1. Increase (+1)");
            System.out.println("2. Decrease (-1)");
            System.out.println("3. Set Target Temperature");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            switch (readInt(sc)) {
                case 1 -> hub.press("TEMP-UP");
                case 2 -> hub.press("TEMP-DOWN");
                case 3 -> {
                    System.out.print("Target temperature (0–100 °C): ");
                    int target = getIntInRange(sc, 0, 100);
                    adjustTemperature(t, target);
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void musicMenu(Scanner sc, CentralHub hub, MusicPlayer p) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Music Player Menu ---");
            System.out.println("Current Volume: " + p.getVolume() + "%");
            System.out.println("1. Increase (+1)");
            System.out.println("2. Decrease (-1)");
            System.out.println("3. Set Target Volume");
            System.out.println("4. Play Sample Playlist");
            System.out.println("5. Stop");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            switch (readInt(sc)) {
                case 1 -> hub.press("VOL-UP");
                case 2 -> hub.press("VOL-DOWN");
                case 3 -> {
                    System.out.print("Target volume (0–100): ");
                    int target = getIntInRange(sc, 0, 100);
                    adjustVolume(p, target);
                }
                case 4 -> p.play("Top Hits");
                case 5 -> p.stop();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void curtainMenu(Scanner sc, CentralHub hub) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Curtain Menu ---");
            System.out.println("1. Open Curtain");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            switch (readInt(sc)) {
                case 1 -> hub.press("CURTAIN-OPEN");
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void showStatus(Light l, Thermostat t, MusicPlayer p) {
        System.out.println("\n--- Current Status ---");
        System.out.println("Brightness : (manual check in Light logs)");
        System.out.println("Temperature: " + t.getTemperature() + "°C");
        System.out.println("Volume     : " + p.getVolume() + "%");
    }

    // ---------- Helpers ----------

    private static void adjustTemperature(Thermostat t, int target) {
        int diff = target - t.getTemperature();
        if (diff > 0) new IncreaseTempCommand(t, diff).execute();
        else if (diff < 0) new DecreaseTempCommand(t, -diff).execute();
        else System.out.println("Already at target temperature.");
    }

    private static void adjustVolume(MusicPlayer p, int target) {
        int diff = target - p.getVolume();
        if (diff > 0) new IncreaseVolumeCommand(p, diff).execute();
        else if (diff < 0) new DecreaseVolumeCommand(p, -diff).execute();
        else System.out.println("Already at target volume.");
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static int getIntInRange(Scanner sc, int min, int max) {
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                sc.next();
            }
            int val = sc.nextInt();
            if (val >= min && val <= max) return val;
            System.out.print("Enter a number between " + min + " and " + max + ": ");
        }
    }
}