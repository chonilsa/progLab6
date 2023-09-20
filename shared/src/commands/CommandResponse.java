package commands;

import java.io.Serializable;

public class CommandResponse implements Serializable {
//    private final String message;
//    private final Object object;
    private String message;
    private Object object;
//    public static boolean flag;
//
//    public CommandResponse(String message, Object object, boolean flag) {
//        this.message = message;
//        this.object = object;
//        CommandResponse.flag = flag;
//    }

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