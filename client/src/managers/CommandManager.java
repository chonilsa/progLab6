package managers;

import commands.CommandDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    HashMap<String, CommandDescription> commands;

    public CommandManager(CommandDescription[] commandDescriptions) {
        commands = new HashMap<>();
        for (CommandDescription commandDescription : commandDescriptions) {
            commands.put(commandDescription.getName(), commandDescription);
        }
    }

//    public void addCommand(AbstractCommand command) {
//        this.commands.put(command.getName(), command);
//    }

    public Map<String, CommandDescription> getCommands() {
        return commands;
    }

    public ArrayList<CommandDescription> getCommandsArray() {
        return new ArrayList<>(commands.values());
    }

    public CommandDescription getCommand(String name) {
        return commands.get(name);
    }
}
