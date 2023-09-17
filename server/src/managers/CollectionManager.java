package managers;

import collections.*;

import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private final LocalDateTime creationTime;
    private final FileManager fileManager;

//    Comparator<SpaceMarine> spaceMarineComparator = (o1, o2) -> o1.compareTo(o2);
    private PriorityQueue<SpaceMarine> spaceMarineCollection;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
//        spaceMarineCollection = new PriorityQueue<>(spaceMarineComparator);
        spaceMarineCollection = new PriorityQueue<>();
        creationTime = LocalDateTime.now();

    }

    public PriorityQueue<SpaceMarine> getSpaceMarineCollection() {
        return spaceMarineCollection;
    }


    public void setSpaceMarineCollection(PriorityQueue<SpaceMarine> spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void add(SpaceMarine obj) {
        this.spaceMarineCollection.add(obj);
    }

    public long getLastId() {
        return spaceMarineCollection.peek().getId();
    }

    public void saveCollection() {
        fileManager.writeCollection(spaceMarineCollection);
    }

    public void loadCollection() {
        this.spaceMarineCollection = fileManager.readCollection();
    }

    public boolean removeById(long id) {
        List<SpaceMarine> list = new ArrayList<>(spaceMarineCollection.size());
        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(spaceMarineCollection);
        while (!priorityQueue2.isEmpty()) {
            list.add(priorityQueue2.poll());
        }
        for (SpaceMarine spm : list) {
            if (spm.getId() == id) {
                spaceMarineCollection.remove(spm);
                return true;
            }
        }
        return false;
    }

//    public boolean addIfMin(SpaceMarine spaceMarine) {
//        long thatId = spaceMarine.getId();



//        ChapterComparator chapterComparator = new ChapterComparator();
//        List<SpaceMarine> list = new ArrayList<>(spaceMarineCollection.size());
//        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(spaceMarineCollection);
//        while (!priorityQueue2.isEmpty()) {
//            list.add(priorityQueue2.poll());
//        }
//        Collections.sort(list, chapterComparator);
//        try {
//            if (list.get(0).getChapter().getMarinesCount() > spaceMarine.getChapter().getMarinesCount()) {
//                spaceMarineCollection.add(spaceMarine);
//                return true;
//            } else {
//                return false;
//            }
//        } catch (IndexOutOfBoundsException ex) {
//            System.err.println("Коллекция не содержит объектов");
//        }
//        return false;
//    }
//    public void removeGreater(SpaceMarine spaceMarine) {
//        spaceMarineCollection.entrySet().removeIf(entry -> entry.getValue().compareTo(humanBeing) > 0);
//    }

    public boolean containsKey(long id) {
        for (SpaceMarine spm : spaceMarineCollection) {
            if (spm.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public SpaceMarine[] getArray() {
        return spaceMarineCollection.toArray(new SpaceMarine[0]);
    }

    public String getFieldNames() {
        return "Список всех полей:\n" +
                "(String) name,\n" +
                "(Integer) coordinate_x,\n" +
                "(float) coordinate_y,\n" +
                "(float) health,\n" +
                "(AstartesCategory) category: " + Arrays.toString(AstartesCategory.values()) + ",\n" +
                "(Weapon) weapon_type: " + Arrays.toString(Weapon.values()) + ",\n" +
                "(MeleeWeapon) melee_weapon: " + Arrays.toString(MeleeWeapon.values()) + ",\n" +
                "(String) chapter_name, \n" +
                "(long) chapter_marines_count";
    }
}
