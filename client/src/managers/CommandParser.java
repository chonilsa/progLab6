package managers;

import java.util.Scanner;

public class CommandParser {
    private static Scanner scanner;
    private static boolean fileMode = false; // default(false) - console mode, true - script mode


    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        CommandParser.scanner = scanner;
    }

    public static boolean fileMode() {
        return fileMode;
    }

    public static void setFileMode() {
        CommandParser.fileMode = true;
    }

    public static void setConsoleMode() {
        CommandParser.fileMode = false;
    }
}
