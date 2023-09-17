package commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandDescription implements Serializable {
    private final String name;
    private final String description;
    private final int argsCount;
    private final ArrayList<Class<?>> argumentTypes;
    private final Class<?> requiredObjectType;

    public CommandDescription(String name, String description, int argumentCount, ArrayList<Class<?>> argumentTypes, Class<?> requiredObjectType) {
        this.name = name;
        this.description = description;
        this.argsCount = argumentCount;
        this.argumentTypes = argumentTypes;
        this.requiredObjectType = requiredObjectType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getArgsCount() {
        return argsCount;
    }

    public List<Class<?>> getArgumentTypes() {
        return argumentTypes;
    }

    public Class<?> getRequiredObjectType() {
        return requiredObjectType;
    }

    @Override
    public String toString() {
        List<Class<?>> classList = Arrays.asList(String.class, Integer.class, Boolean.class);

        String concatenatedNames = classList.stream()
                .map(Class::getName)
                .collect(Collectors.joining(", "));

        return name + " : " + description;
    }
}
