package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "info", description = "Prints info about collection")
public class Info extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public Info(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("info", "Get info about collection.");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public String describe() {
        return "Get info about collection.";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.info(args, obj);
    }
}
