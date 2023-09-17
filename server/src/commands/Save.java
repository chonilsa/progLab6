package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "save", description = "Save collection to file.")
public class Save extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public Save(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("save", "Save collection to file.");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.save(args, obj);
    }

}
