package xyz.sk1.bukkit.prisonextra.internal;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.reflections.Reflections;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
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

    @SneakyThrows
    public void registerExecutors(String packageName){
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Executor>> subTypes = reflections.getSubTypesOf(Executor.class);

        for (Class<? extends Executor> command : subTypes){

            Executor executor = command.newInstance();

            try {
                Core.getInstance().getCommand(executor.getAttributes().name())
                        .setExecutor(executor);
            } finally {
                Utils.LOG.info(command.getDeclaredAnnotation(Attributes.class).name()+" executor was registered");
            }
        }
    }

}
