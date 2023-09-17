package askers;

import managers.CommandParser;

public class PortAsker {
    public static int askPort() {
        while (true) {
            System.out.println("Enter port: ");
            try {
                int port = Integer.parseInt(CommandParser.getScanner().nextLine().trim());
                if (port > 0 && port < 65536) {
                    return port;
                } else {
                    System.out.println("Port must be in range (0, 65536)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Port must be a number");
            }
        }
    }
}
