package xyz.sk1.bukkit.prisonextra.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.MinecraftKey;
import net.minecraft.server.v1_8_R3.RegistryMaterials;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Utils {

    public static final Logger LOG = Logger.getLogger("PrisonExtra");
    public static final String colorize(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static ExecutorService pool = Executors.newCachedThreadPool();

}
