package xyz.sk1.bukkit.prisonextra.utilities;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

}