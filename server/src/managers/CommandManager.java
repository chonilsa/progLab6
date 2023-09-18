package managers;

import commands.AbstractCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final Map<String, AbstractCommand> commands;
    private final List<String> commandHistory = new ArrayList<>();


    public CommandManager(List<AbstractCommand> commands) {
        this.commands = new HashMap<>();
        for (AbstractCommand command : commands) {
            addCommand(command);
        }
    }

    public void addCommand(AbstractCommand command) {
        this.commands.put(command.getName(), command);
    }

    public List<String> getCommandHistory() {
        return commandHistory.subList(commandHistory.size() - Math.min(commandHistory.size(), 13), commandHistory.size());
//        return commandHistory;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public void addHistory(String command) {
        commandHistory.add(command);
    }

    public AbstractCommand[] getCommandsArray() {
        return commands.values().toArray(new AbstractCommand[0]);
    }
}
