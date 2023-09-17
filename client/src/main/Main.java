package main;

import askers.PortAsker;
import commands.CommandDescription;
import commands.CommandRequest;
import commands.CommandResponse;
import managers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new managers.UserConsole();
        CommandParser.setScanner(new Scanner(System.in));

        int port = PortAsker.askPort();
        Client client = new Client("localhost", port);

        CommandRequest request = new CommandRequest("connect", new String[]{}, null);
        CommandResponse commandResponse = client.run(request);

        ArrayList<CommandDescription> commandDescriptions = (ArrayList<CommandDescription>) commandResponse.getObject();
        CommandDescription[] commandsArray = new CommandDescription[commandDescriptions.size() - 1];
        for (int i = 0; i < commandDescriptions.size(); i++) {
            if (commandDescriptions.get(i).getName().equals("connect")) {
                commandDescriptions.remove(i);
                break;
            }
        }
        for (int i = 0; i < commandDescriptions.size(); i++) {
            commandsArray[i] = commandDescriptions.get(i);
        }
        CommandManager commandManager = new CommandManager(commandsArray);
        Executor executor = new Executor(commandManager, console, client);

        executor.consoleMode();
    }
}
