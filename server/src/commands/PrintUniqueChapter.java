package commands;

import collections.Chapter;
import managers.CollectionManager;
import managers.CommandReceiver;

@CommandInfo(name = "print_unique_chapter", description = "print unique values of chapter field of all elements of collection", argsCount = 0, argumentTypes = {Chapter.class})
public class PrintUniqueChapter extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final CommandReceiver commandReceiver;
    public PrintUniqueChapter(CollectionManager collectionManager, CommandReceiver commandReceiver) {
        super("print_unique_chapter", "print unique values of chapter field of all elements of collection");
        this.collectionManager = collectionManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.printUniqueChapter(args, obj);
    }

}
