package collections;

import java.io.Serializable;

public enum AstartesCategory implements Serializable {
    INCEPTOR,
    TACTICAL,
    LIBRARIAN,
    HELIX;

    public static String names() {
        String names = "";
        for (AstartesCategory astartesCategory : AstartesCategory.values()) {
            names += astartesCategory.name() + ", ";
        }
        return names.substring(0, names.length() - 2);
    }

    public int getCategoryOrdinal(){
        return this.ordinal();
    }
}
