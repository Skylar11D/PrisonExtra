package xyz.sk1.bukkit.prisonextra.executors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ACommand {

    String name();

    boolean requiresPlayer() default false;

    String permission() default "";

}
