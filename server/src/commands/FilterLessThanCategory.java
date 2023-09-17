package commands;

import collections.AstartesCategory;
import collections.SpaceMarine;
import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "filter_less_than_category", description = "print all elements of the category field value that are less than specified value", argsCount = 0, requiredObjectType = AstartesCategory.class)
public class FilterLessThanCategory extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public FilterLessThanCategory(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("filter_less_than_category", "print all elements of the category field value that are less than specified value");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }


    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.filterLessThanCategory(args, obj);
    }
}
