package collections;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import other.Validatable;

import java.io.Serializable;

public class Chapter implements Validatable, Serializable {

    //    @CsvBindByName(column = "ChapterName")
//    @CsvBindByPosition(position = 10)
    private String name; //Поле не может быть null, Строка не может быть пустой

    //    @CsvBindByName(column = "MarinesCount")
//    @CsvBindByPosition(position = 11)
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public Chapter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMarinesCount() {
        return marinesCount;
    }

    public void setMarinesCount(long marinesCount) {
        this.marinesCount = marinesCount;
    }

    @Override
    public boolean validate() {
        return name != null && !name.equals("") && (marinesCount > 0 && marinesCount <= 1000);
    }

//    @Override
//    public String toString() {
//        return "(name = " + name + ", marinesCount = " + marinesCount + ")";
//    }

    @Override
    public String toString() {
        return name + "," + marinesCount;
    }
}
