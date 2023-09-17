package commands;

import collections.SpaceMarine;
import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "add", description = "Adds element to the collection. Syntax: add {element}", argsCount = 0, requiredObjectType = SpaceMarine.class)
public class AddElement extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public AddElement(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("add", "add element");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public String describe(){
        return "Adds element to the collection. Syntax: add {element}";
    }

    @Override
    public String getName(){
        return "add";
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.add(args, obj);
    }
}
