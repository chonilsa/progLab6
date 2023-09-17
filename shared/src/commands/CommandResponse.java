package commands;

import java.io.Serializable;

public class CommandResponse implements Serializable {
    private final String message;
    private final Object object;

    public CommandResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }
}