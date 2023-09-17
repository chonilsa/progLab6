package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "head", description = "print first element of collection")
public class Head extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Head(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("head", "print first element of collection");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.head(args, obj);
    }
}
