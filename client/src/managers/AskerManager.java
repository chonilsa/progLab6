package managers;

import askers.AbstractAsker;

import java.util.HashMap;
import java.util.Map;

public class AskerManager {
    private static Map<Object, AbstractAsker> askers = new HashMap<>();

    public static AbstractAsker getAsker(Object object) {
        return askers.get(object);
    }

    public static void setAsker(Object object, AbstractAsker asker) {
        askers.put(object, asker);
    }

    public static void setAskers(Map<Object, AbstractAsker> askers) {
        AskerManager.askers = askers;
    }
}
