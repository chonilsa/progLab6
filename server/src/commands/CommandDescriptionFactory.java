package commands;

import java.util.ArrayList;

public class CommandDescriptionFactory {

    public static CommandDescription createCommandDescription(Class<? extends AbstractCommand> commandClass) {
        CommandInfo commandInfo = commandClass.getAnnotation(CommandInfo.class);
        if (commandInfo == null) {
            throw new IllegalArgumentException("Command class is not annotated with CommandInfo");
        }

        String name = commandInfo.name();
        String description = commandInfo.description();
        int argsCount = commandInfo.argsCount();
        Class<?>[] argumentTypes = commandInfo.argumentTypes();
        Class<?> requiredObjectType = commandInfo.requiredObjectType();

        ArrayList<Class<?>> argumentTypeList = new ArrayList<>();
        for (Class<?> argumentType : argumentTypes) {
            argumentTypeList.add(argumentType);
        }

        return new CommandDescription(name, description, argsCount, argumentTypeList, requiredObjectType);
    }
}
