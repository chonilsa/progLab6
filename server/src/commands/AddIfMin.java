package commands;

import collections.SpaceMarine;
import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "add_if_min", description = "Adds element to the collection if it's value is minimal. Syntax: add_if_min {element}", argsCount = 0, requiredObjectType = SpaceMarine.class)
public class AddIfMin extends AbstractCommand{

    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public AddIfMin(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("add_if_min", "add element if min");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public String describe(){
        return "Adds element to the collection if it's value is minimal. Syntax: add {element}";
    }

    @Override
    public String getName(){
        return "add_if_min";
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.addIfMin(args, obj);
    }
}
