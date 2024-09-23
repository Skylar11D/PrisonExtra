package xyz.sk1.bukkit.prisonextra.enchantments.manager;

import org.bukkit.enchantments.Enchantment;
import xyz.sk1.bukkit.prisonextra.internal.PluginManager;
import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.*;

public class EnchantmentManager implements Manager {

    private Map<Integer, Enchantment> enchantedCache;
    private PluginManager pluginManager;

    public EnchantmentManager(PluginManager instance){
        this.enchantedCache = new HashMap<>();
        this.pluginManager = instance;
    }

    public void registerEnchantment(Enchantment enchantment){
        this.enchantedCache.put(enchantment.getId(), enchantment);
        Utils.registerEnchantment(enchantment);
    }

    public void unregisterEnchantment(Enchantment enchantment){
        this.enchantedCache.remove(enchantment.getId());
        Utils.safelyUnregisterEnchantments(enchantment);
    }

    public Enchantment getEnchantment(int byId){

        if(enchantedCache.containsKey(byId))
            return enchantedCache.get(byId);

        return null;
    }

    @Override
    public void load() {
        Utils.LOG.info("Tracking and loading enchantments into the cache...");

        pluginManager.registerEnchantments("xyz.sk1.bukkit.prisonextra.enchantments", this);

    }

    @Override
    public ManagerType getType() {
        return ManagerType.ENCHANTMENTS;
    }

    @Override
    public void finish() {

        this.enchantedCache.values().forEach(this::unregisterEnchantment);

    }
}
