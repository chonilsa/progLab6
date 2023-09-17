package commands;

import java.io.Serializable;

public class CommandRequest implements Serializable {
    private final String commandName;
    private final String[] arguments;
    private final Object object;

    public CommandRequest(String commandName, String[] arguments, Object object) {
        this.commandName = commandName;
        this.arguments = arguments;
        this.object = object;
    }


    public String getCommandName() {
        return commandName;
    }

    public String[] getArguments() {
        return arguments;
    }

    public Object getElement() {
        return object;
    }
}
