package managers;

import collections.*;
import commands.AbstractCommand;
import commands.CommandDescription;
import commands.CommandDescriptionFactory;
import commands.CommandResponse;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CommandReceiver {
    private final CollectionManager collectionManager;

    public CommandReceiver(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public CommandResponse help(String[] args, Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        AbstractCommand[] commands = (AbstractCommand[]) obj;
        Arrays.stream(commands)
                .filter(command -> !command.getName().equals("save"))
                .forEach(command -> stringBuilder.append(command.getName()).append(" - ").append(command.describe()).append("\n"));

        stringBuilder.append("help - ").append("shows help for available commands");
        return new CommandResponse(stringBuilder.toString(), null);
    }


    public CommandResponse save(String[] args, Object obj) {
        collectionManager.saveCollection();
        return new CommandResponse("Collection saved", null);
    }


    public CommandResponse removeById(String[] args, Object obj) {
        long id = Long.parseLong(args[0]);
        boolean isRemoved = collectionManager.removeById(id);

        String message = isRemoved ? "Element with id " + id + " removed" : "Element with id " + id + " wasn't found";

        return new CommandResponse(message, null);

    }

    public CommandResponse info(String[] args, Object obj) {
        LocalDateTime initTime = collectionManager.getCreationTime();
        String message = "Collection info:\n" + "Collection type: " + collectionManager.getSpaceMarineCollection().getClass().getName() + "\n" + "Collection size: " + collectionManager.getSpaceMarineCollection().size() + "\n" + "Initialization time: " + initTime;
        return new CommandResponse(message, null);
    }

    public CommandResponse show(String[] args, Object obj) {
        List<SpaceMarine> elements = Arrays.stream(collectionManager.getArray())
                .sorted(Comparator.reverseOrder())
                .toList();

        return new CommandResponse("Showed all elements of collection", elements);
    }

    public CommandResponse clear(String[] args, Object object) {
        collectionManager.setSpaceMarineCollection(new PriorityQueue<>());
        return new CommandResponse("Collection was cleared", null);
    }

    public CommandResponse update(String[] args, Object obj) {
        long thatId = Long.parseLong(args[0]);
        Optional<SpaceMarine> optionalHumanBeing = Arrays.stream(collectionManager.getArray())
                .filter(spaceMarine -> spaceMarine.getId() == thatId)
                .findFirst();

        if (optionalHumanBeing.isEmpty()) {
            return new CommandResponse("Element with this Id doesn't exist.", null);
        }
        SpaceMarine spaceMarine = (SpaceMarine) obj;
        spaceMarine.setId(thatId);
        collectionManager.removeById(thatId);
        collectionManager.add(spaceMarine);
        return new CommandResponse("Element updated successfully.", null);
    }

    public CommandResponse add(String[] args, Object object) {
//        Optional<SpaceMarine> optionalSpaceMarine =collectionManager.getSpaceMarineCollection().stream()
//                .filter(spaceMarine -> spaceMarine.getId() == )
        SpaceMarine spaceMarine = (SpaceMarine) object;
        collectionManager.add(spaceMarine);
        return new CommandResponse("Element added successfully", null);
    }

    //not yet
    public CommandResponse addIfMin(String[] args, Object object) {
//        Optional<SpaceMarine> optionalSpaceMarine =collectionManager.getSpaceMarineCollection().stream()
//                .filter(spaceMarine -> spaceMarine.getId() == )
//        ChapterComparator chapterComparator = new ChapterComparator();
//        List<SpaceMarine> list = new ArrayList<>();
//        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(collectionManager.getSpaceMarineCollection());
//        while (!priorityQueue2.isEmpty()) {
//            list.add(priorityQueue2.peek());
//        }
//        Collections.sort(list, chapterComparator);
//
//        if (list.get(0).getChapter().getMarinesCount() > spaceMarine.getChapter().getMarinesCount()) {
//            priorityQueue.add(spaceMarine);
//            return new CommandResponse("",null);
//        } else {
//            return new CommandResponse("",null);
//        }

        SpaceMarine marine = (SpaceMarine) object;

        List<SpaceMarine> list = Arrays.stream(collectionManager.getArray())
                .filter(spaceMarine -> marine.compareTo(spaceMarine) == -1)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            collectionManager.add(marine);
            return new CommandResponse("Element added successfully", null);
        } else {
            return new CommandResponse("Element not added, BC value of element is greater than min in collection", null);
        }
    }

    public CommandResponse printFieldDescending(String[] args, Object object) {
        CategoryComparator categoryComparator = new CategoryComparator();
        List<SpaceMarine> elements = Arrays.stream(collectionManager.getArray())
//                .sorted((o1, o2)->(o1.getCategory().getCategoryOrdinal()).compareTo(o2.getCategory().getCategoryOrdinal()))
                .sorted(categoryComparator::compare)
                .collect(Collectors.toList());
        List<AstartesCategory> list = elements.stream()
                .map(SpaceMarine::getCategory)
                .collect(Collectors.toList());
        return new CommandResponse("Fields of category in descending order", list);
    }

    public CommandResponse head(String[] args, Object object) {
        SpaceMarine spaceMarine = collectionManager.getSpaceMarineCollection().peek();
        String str = spaceMarine.toString();
        return new CommandResponse("First element of collection", str);

    }

    public CommandResponse printUniqueChapter(String[] args, Object object) {
        List<Chapter> spaceMarines = Arrays.stream(collectionManager.getArray())
                .map(spaceMarine -> spaceMarine.getChapter())
                .collect(Collectors.toList());
        List<Chapter> list = spaceMarines.stream()
                .filter(chapter -> Collections.frequency(spaceMarines, chapter) == 1)
                .collect(Collectors.toList());

        return new CommandResponse("Unique chapter/s", list);

    }


    public CommandResponse filterLessThanCategory(String[] args, Object object) {
//        AstartesCategory category = AstartesCategory.valueOf(args[0]);
        AstartesCategory category = (AstartesCategory) object;
        int thatFieldOrdinal = category.getCategoryOrdinal();
        List<SpaceMarine> list = Arrays.stream(collectionManager.getArray())
                .filter(spaceMarine -> spaceMarine.getCategory().getCategoryOrdinal() < thatFieldOrdinal)
                .collect(Collectors.toList());

//                .filter(astartesCategory -> astartesCategory.getCategoryOrdinal()<thatFieldOrdinal)
//                .findAny()
//                .orElse(null);

//                .filter(astartesCategory -> (astartesCategory.getCategoryOrdinal() < thatFieldOrdinal) ? astartesCategory.toString() )
//                .collect(Collectors.toList());
        return new CommandResponse("Category field values less than the specified one", list);

    }

    public CommandResponse history(String[] args, Object obj) {
        CommandManager commandManager = (CommandManager) obj;
        String commands = Arrays.stream(commandManager.getCommandHistory().toArray())
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        return new CommandResponse("Last 13 commands: ", commands);
    }

    public CommandResponse connect(String[] args, Object obj) {
        CommandManager commandManager = (CommandManager) obj;
        AbstractCommand[] commands = commandManager.getCommandsArray();
        List<CommandDescription> commandDescriptions = Arrays.stream(commands)
                .filter(command -> !command.getName().equals("save"))
                .map(command -> CommandDescriptionFactory.createCommandDescription(command.getClass()))
                .collect(Collectors.toList());

        return new CommandResponse("Got commands", commandDescriptions);
    }

//    public CommandResponse addElement(String[] args, Object obj) {
//        CommandManager commandManager = (CommandManager) obj;
//        return null;
//    }
}