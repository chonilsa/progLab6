package commands;

import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "print_field_descending_category", description = "print all fields of category in descending order")
public class PrintFieldDescendingCategory extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;

    public PrintFieldDescendingCategory(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("print_field_descending_category", "print all fields of category in descending order");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.printFieldDescending(args, obj);
    }

}
