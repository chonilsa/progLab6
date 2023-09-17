package collections;

import java.util.Comparator;

public class CategoryComparator implements Comparator<SpaceMarine> {
    @Override
    public int compare(SpaceMarine spm1, SpaceMarine spm2) {
        return spm2.getCategoryOrdinal() - spm1.getCategoryOrdinal();
    }
}
