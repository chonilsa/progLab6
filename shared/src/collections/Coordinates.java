package collections;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import other.Validatable;

import java.io.Serializable;

public class Coordinates implements Validatable, Serializable {

//    @CsvBindByName(column = "Coordinates_X")
//    @CsvBindByPosition(position = 3)
    private Integer x;

//    @CsvBindByName(column = "Coordinates_Y")
//    @CsvBindByPosition(position = 4)
    private float y;

    public Coordinates(float y) {
        this.y = y;
    }

    public Coordinates(Integer x) {
        this.x = x;
    }

    public Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

//    @Override
//    public String toString() {
//        return "coordinates (x, y) = (" + getX() + ", " + getY() + ")";
//    }

    @Override
    public String toString() {
        return getX() + "," + getY();
    }

    public boolean validate() {
        return x != null;
    }
}