package xyz.sk1.bukkit.prisonextra.utilities;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Utils {

    public static final Logger LOG = Logger.getLogger("PrisonExtra");

    public static String colorize(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static final ExecutorService POOL = Executors.newCachedThreadPool();

    public static boolean hasTag(ItemStack item, String key){

        net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        Utils.LOG.info("its nms copy: "+nmsCopy.getTag());

        if(!nmsCopy.hasTag())
            return false;

        NBTTagCompound nbtTag = nmsCopy.getTag();

        if (!nbtTag.hasKey(key))
            return false;

        return true;
    }

    public static String getTag(ItemStack item, String key){

        net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);

        if(!nmsCopy.hasTag())
            return null;

        NBTTagCompound nbtTag = nmsCopy.getTag();

        if(!nbtTag.hasKey(key))
            return null;

        return nbtTag.getString(key);
    }

    public static void registerEnchantment(Enchantment enchantment){

        try {
            Field accepting = Enchantment.class.getDeclaredField("acceptingNew");

            accepting.setAccessible(true);
            accepting.set(null, true);

            Enchantment.registerEnchantment(enchantment);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @SuppressWarnings("unchecked")
    public static void safelyUnregisterEnchantments(Enchantment enchantment){

        try {

            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            Map<Integer, Enchantment> byId = (Map<Integer, Enchantment>) byIdField.get(null);
            Map<Integer, Enchantment> byName = (Map<Integer, Enchantment>) byNameField.get(null);

            if(byId.containsKey(enchantment.getId()))
                byId.remove(enchantment);

            if(byName.containsKey(enchantment.getId()))
                byName.remove(enchantment);

        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

    }

    public static int generateRandomId() {
        return ThreadLocalRandom.current().nextInt(1, 1000);
    }

}