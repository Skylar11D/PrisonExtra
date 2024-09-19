package xyz.sk1.bukkit.prisonextra.internal;

import org.bukkit.Bukkit;
import org.reflections.Reflections;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class PluginManager {

    public void registerListeners(String packageName){

        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends BaseListener>> listeners = reflections.getSubTypesOf(BaseListener.class);

        for (Class<? extends BaseListener> listener : listeners){

            try {
                Bukkit.getPluginManager().registerEvents(
                        listener.getDeclaredConstructor().newInstance(), Core.getInstance()
                );
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            } finally {
                Utils.LOG.info(listener.getSimpleName() + " event listener was registered");
            }
        }

    }

    public void registerExecutors(String packageName){
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Executor>> subTypes = reflections.getSubTypesOf(Executor.class);

        for (Class<? extends Executor> command : subTypes){
            try {
                Core.getInstance().getCommand(command.getDeclaredAnnotation(Attributes.class).name()).setExecutor(command.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                Utils.LOG.info(command.getDeclaredAnnotation(Attributes.class).name()+" executor was registered");
            }
        }
    }

}
