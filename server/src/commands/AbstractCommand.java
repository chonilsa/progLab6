package commands;

public abstract class AbstractCommand implements Command {

    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String describe() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}
