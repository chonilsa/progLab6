package collections;

import java.io.Serializable;

public enum Weapon implements Serializable {
    MELTAGUN,
    COMBI_PLASMA_GUN,
    FLAMER,
    GRAV_GUN;

    public static String names() {
        String names = "";
        for (Weapon weapon : Weapon.values()) {
            names += weapon.name() + ", ";
        }
        return names.substring(0, names.length() - 2);
    }
}
