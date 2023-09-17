package commands;

public interface Command {
    String describe();

    String getName();

    CommandResponse execute(String[] args, Object object);
}
