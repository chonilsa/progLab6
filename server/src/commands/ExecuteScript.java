package commands;


import managers.CollectionManager;
import managers.CommandReceiver;

import java.io.File;

@CommandInfo(name = "execute_script", description = "Execute script from file", argsCount = 1, argumentTypes = {String.class}, requiredObjectType = Void.class)
public class ExecuteScript extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public boolean flag;//false - first time, true - inside first

    public ExecuteScript(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("execute_script", "Execute script from file");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
        this.flag = false;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        if (args.length == 0) {
            return new CommandResponse("No file name specified, usage: execute_script <file_name>", false);
        }
        if (args.length > 1) {
            return new CommandResponse("Too many arguments, usage: execute_script <file_name>", false);
        }
        String fileName = args[0];
//        if (!(new File(fileName).exists())) return new CommandResponse("Execution was terminated", false);
        return commandReceiver.executeScript(args, obj);
//        return new CommandResponse("Executing script from file " + fileName, true);
    }
}