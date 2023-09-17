package collections;

import java.util.Comparator;

public class ChapterComparator implements Comparator<SpaceMarine> {
    @Override
    public int compare(SpaceMarine spm1, SpaceMarine spm2) {
        return (int) (spm1.getChapter().getMarinesCount() - spm2.getChapter().getMarinesCount());
    }
}
