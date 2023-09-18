package managers;

import collections.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import exceptions.UniqueIdException;
import exceptions.ValidValuesException;

import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {
    private final String path;

    public FileManager(String path) {
        this.path = path;
    }


    public void writeCollection(PriorityQueue<SpaceMarine> collection) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        CSVWriter csvWriter = null;
        ArrayList<SpaceMarine> list = new ArrayList<>();
        collection.forEach(i -> list.add(i));
//        Collections.reverse(list);
//        Collections.sort(collection, );
        try {

            fos = new FileOutputStream((path));
            osw = new OutputStreamWriter(fos);
            csvWriter = new CSVWriter(osw);
            List<String[]> strings = list.stream()
                    .map(spaceMarine -> spaceMarine.toString())
                    .map(str -> new String[]{str})
                    .toList();
//            for (int i = 0; i < list.size(); i++) {
//                Date date = Date.from(Instant.ofEpochMilli(Long.parseLong(arrayList.get(i).getCreationDate())));
//                list.get(i).setCreationDate();

            csvWriter.writeAll(strings);
            csvWriter.close();
            fos.close();
            osw.close();

        } catch (IOException e) {
            System.out.println("Error during opening file: " + e.getMessage());
        }
    }

    public PriorityQueue<SpaceMarine> readCollection() {
        FileReader fr = null;
        PriorityQueue<SpaceMarine> priorityQueue = null;
        if (path != null && !path.isEmpty()) {
            try {
                fr = new FileReader(path);
                CSVReader csvReader = new CSVReader(fr);
                List<String[]> allData = csvReader.readAll();
                ArrayList<SpaceMarine> spaceMarines = parseFromListOfStringArrays(allData);
                priorityQueue = new PriorityQueue<>();
                priorityQueue.addAll(spaceMarines);
                csvReader.close();
                fr.close();
                return priorityQueue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }

        }
        return priorityQueue;
    }

    public ArrayList<SpaceMarine> parseFromListOfStringArrays(List<String[]> allData) {
        HashSet<Long> hashSet = new HashSet<>();
        ArrayList<SpaceMarine> arrayList = new ArrayList<>();
        long id;
        String name;
        Integer x;
        float y;
        Date date;
        float health;
        AstartesCategory category;
        Weapon weaponType;
        MeleeWeapon meleeWeapon;
        String chapterName;
        long marinesCount;
        List<String[]> strings = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
//            System.out.println(allData.get(i).length);
            String[] s = Arrays.toString(allData.get(i)).substring(1, Arrays.toString(allData.get(i)).length() - 1).split(",");
            strings.add(s);
        }
        for (String row[] : strings) {
            try {
//                row[] =row[0].substring(1, row[0].length() - 1).split(",");
                id = Long.parseLong(row[0]);
                for (Long i : hashSet) {
                    if (i == id) {
                        throw new UniqueIdException();
                    }
                }
                hashSet.add(id);
                name = row[1].trim();
                x = Integer.valueOf(row[2]);
                y = Float.parseFloat(row[3]);
                date = Date.from(Instant.ofEpochMilli(Long.parseLong(row[4])));
                health = Float.parseFloat(row[5]);
                category = AstartesCategory.valueOf(row[6]);
                weaponType = Weapon.valueOf(row[7]);
                meleeWeapon = MeleeWeapon.valueOf(row[8]);
                chapterName = row[9];
                marinesCount = Long.parseLong(row[10]);
                arrayList.add(new SpaceMarine(id, name, new Coordinates(x, y),
                        date, health, category, weaponType,
                        meleeWeapon, new Chapter(chapterName, marinesCount)));
            } catch (UniqueIdException ex) {
                System.err.println("Unique of id is violated, check the values of these fields in the file");
            } catch (RuntimeException ex) {
                System.err.println("There are invalid values in the file, the fields written in the lines with such values will not be taken into account");
            }
        }
        System.out.println();
        return arrayList;
    }

}
