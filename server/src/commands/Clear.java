package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "clear", description = "clear collection", argsCount = 0, argumentTypes = {}, requiredObjectType = Void.class)
public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public Clear(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object object) {
        return commandReceiver.clear(args, object);
    }
}
