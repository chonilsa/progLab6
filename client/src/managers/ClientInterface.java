package managers;

import commands.CommandRequest;
import commands.CommandResponse;

public interface ClientInterface {
    public CommandResponse run(CommandRequest obj);
}
