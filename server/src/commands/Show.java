package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "show", description = "Show all elements of collection")
public class Show extends AbstractCommand {
    final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public Show(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("show", "Show all elements of collection");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.show(args, obj);
    }

}