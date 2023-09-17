package commands;

import collections.SpaceMarine;
import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "update", description = "update element by id", argsCount = 1, requiredObjectType = SpaceMarine.class, argumentTypes = {long.class})
public class UpdateId extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public UpdateId(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("update", "update element by id");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.update(args, obj);
    }
}
