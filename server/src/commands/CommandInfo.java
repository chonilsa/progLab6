package commands;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String description();
    int argsCount() default 0;
    Class<?>[] argumentTypes() default {};
    Class<?> requiredObjectType() default Void.class;
}
