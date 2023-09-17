package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "remove_by_id", description = "remove element from collection by id", argsCount = 1, argumentTypes = {long.class}, requiredObjectType = Void.class)
public class RemoveById extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public RemoveById(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("remove", "remove element from collection by id");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.removeById(args, obj);
    }
}
