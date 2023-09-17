package collections;

import java.io.Serializable;

public enum MeleeWeapon implements Serializable {
    POWER_SWORD,
    CHAIN_AXE,
    MANREAPER,
    LIGHTING_CLAW,
    POWER_FIST;

    public static String names() {
        String names = "";
        for (MeleeWeapon meleeWeapon : MeleeWeapon.values()) {
            names += meleeWeapon.name() + ", ";
        }
        return names.substring(0, names.length() - 2);
    }
}
