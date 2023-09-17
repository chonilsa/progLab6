package commands;

import managers.CommandManager;
import managers.CommandReceiver;

@CommandInfo(name = "history", description = "Shows last 13 commands (without their arguments)")
public class History extends AbstractCommand {
    private final CommandManager commandManager;
    private final CommandReceiver commandReceiver;

    public History(CommandManager commandManager, CommandReceiver commandReceiver) {
        super("history", "Shows last 13 commands (without their arguments)");
        this.commandManager = commandManager;
        this.commandReceiver = commandReceiver;
    }

    @Override
    public String describe() {
        return "Show last 13 commands (without their arguments)";
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public CommandResponse execute(String[] args, Object obj) {
        return commandReceiver.history(args, obj);
    }
}
