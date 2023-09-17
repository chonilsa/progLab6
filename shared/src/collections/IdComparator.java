package collections;

import java.util.Comparator;

public class IdComparator implements Comparator<SpaceMarine> {

    @Override
    public int compare(SpaceMarine o1, SpaceMarine o2) {
        if (o1.getId() == o2.getId()){
            return 0;
        } else if (o1.getId() > o2.getId()) {
            return 1;
        } else {
            return -1;
        }
    }
}
