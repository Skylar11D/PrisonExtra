package xyz.sk1.bukkit.prisonextra.internal;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.reflections.Reflections;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.enchantments.manager.EnchantmentManager;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class PluginManager {

    public PluginManager(){
        System.setProperty("org.slf4j.simpleLogger.log.org.reflections", "off");
    }

    public void registerListeners(String packageName){

        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends BaseListener>> listeners = reflections.getSubTypesOf(BaseListener.class);

        Utils.LOG.info("\nRegistering events listeners..\n");

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

        Utils.LOG.info("\nRegistering commands executors..\n");

        for (Class<? extends Executor> command : subTypes){

            try {

                Executor executor = command.newInstance();

                Core.getInstance().getCommand(executor.getAttributes().name())
                        .setExecutor(executor);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                Utils.LOG.info( "/"+ command.getDeclaredAnnotation(Attributes.class).name()+" executor was registered");
            }
        }
    }

    public void registerEnchantments(String packageName, EnchantmentManager enchantmentManager){

        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Enchantment>> subTypes = reflections.getSubTypesOf(Enchantment.class);

        Utils.LOG.info("\nRegistering enchantments..\n");

        int trackedIds = 0;

        for (Class<? extends Enchantment> enchantment : subTypes){
            try {


                Enchantment enchant = enchantment
                        .getDeclaredConstructor(int.class)
                        .newInstance(++trackedIds);

                enchantmentManager.registerEnchantment(enchant);

            } catch (IllegalArgumentException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } finally {
                Utils.LOG.info(enchantment.getSimpleName() + " enchantment was registered");
            }
        }
    }

}
