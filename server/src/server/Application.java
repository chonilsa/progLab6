package server;

import collections.Chapter;
import collections.Coordinates;
import collections.SpaceMarine;
import managers.CollectionManager;
import managers.FileManager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;


public class Application {
    public static void main(String[] args) {
//        FileManager fileManager = new FileManager("C:\\Users\\79534\\IdeaProjects\\progLab6\\server\\src\\collection.csv");
//        PriorityQueue<SpaceMarine> list = new PriorityQueue<>();
//        list = fileManager.readCollection();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.poll());
//        }

        String path = null;
        try {
            path = args[0];
//            String path2 = "C:\\Users\\79534\\IdeaProjects\\progLab6\\server\\src\\collection.csv";
//            System.out.println(path);
//            System.out.println(path2);
        } catch (Exception e) {
            System.out.println("Program variable is not set or is set incorrectly");
            System.exit(1);
        }
        if (path == null) {
            System.out.println("Program variable is not set");
            System.exit(1);
        }
        FileManager fileManager = new FileManager(path);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        try {
            collectionManager.loadCollection();
        } catch (Exception e) {
            System.out.println("Error while loading collection from file");
            System.exit(1);
        }


        Server server = new Server(collectionManager);

        server.run();


//        FileManager fileManager = new FileManager("C:\\Users\\79534\\IdeaProjects\\progLab6\\server\\src\\collection.csv");
//        PriorityQueue<SpaceMarine> spaceMarines = new PriorityQueue<>();
//        Coordinates coordinates = new Coordinates(4,3f);
//        Chapter chapter = new Chapter("fas4", 4);
//        spaceMarines.add(new SpaceMarine("4", coordinates, 3.0f, HELIX, FLAMER, MANREAPER, chapter));
//
//        fileManager.writeCollection(spaceMarines);

//        System.out.println(Date.from(Instant.ofEpochMilli(new Date().getTime())));
//        System.out.println(new Date().getTime());
    }
}
