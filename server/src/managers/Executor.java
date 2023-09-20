package managers;

import commands.*;

import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final List<String> recursionStack = new ArrayList<>();
    private CommandManager commandManager;
    private CommandReceiver commandReceiver;

    public Executor(CollectionManager collectionManager) {
        List<AbstractCommand> commands = new ArrayList<>();
        commandReceiver = new CommandReceiver(collectionManager);
        commands.add(new Info(collectionManager, commandReceiver));
        commands.add(new Save(collectionManager, commandReceiver));
        commands.add(new Show(collectionManager, commandReceiver));
        commands.add(new Clear(collectionManager, commandReceiver));
        commands.add(new AddElement(collectionManager, commandReceiver));
        commands.add(new AddIfMin(collectionManager, commandReceiver));
        commands.add(new PrintFieldDescendingCategory(collectionManager, commandReceiver));
        commands.add(new Head(collectionManager, commandReceiver));
        commands.add(new PrintUniqueChapter(collectionManager, commandReceiver));
        commands.add(new RemoveById(collectionManager, commandReceiver));
        commands.add(new FilterLessThanCategory(collectionManager, commandReceiver));
        commands.add(new UpdateId(collectionManager, commandReceiver));
        commands.add(new ExecuteScript(collectionManager, commandReceiver));

        this.commandManager = new CommandManager(commands);
        commandManager.addCommand(new History(commandManager, commandReceiver));
        commandManager.addCommand(new Help(commandManager.getCommandsArray(), commandReceiver));
        commandManager.addCommand(new Connect(commandManager, commandReceiver));
//        commandManager.addCommand(new ExecuteScript());
        this.commandReceiver = new CommandReceiver(collectionManager);

    }

    public AbstractCommand[] getCommandsArray() {
        return commandManager.getCommandsArray();
    }

    public CommandResponse executeCommand(CommandRequest userCommand) {
        Command command = commandManager.getCommands().get(userCommand.getCommandName());
        CommandResponse response = new CommandResponse("OK", null);
        try {
            if (userCommand.getElement() == null) {
                if (userCommand.getCommandName().equals("history")) {
                    response = command.execute(userCommand.getArguments(), commandManager);
                    commandManager.addHistory(userCommand.getCommandName());
                } else if (userCommand.getCommandName().equals("execute_script")) {
                    response = command.execute(userCommand.getArguments(), null);
                }
                else {
                    response = command.execute(userCommand.getArguments(), null);
                    commandManager.addHistory(userCommand.getCommandName());
                }

            } else {
                response = command.execute(userCommand.getArguments(), userCommand.getElement());
                commandManager.addHistory(userCommand.getCommandName());
            }
            Command save = commandManager.getCommands().get("save");
            save.execute(new String[]{}, null);
            return response;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public enum Status {
        OK,
        ERROR,
        EXIT,
    }
}
