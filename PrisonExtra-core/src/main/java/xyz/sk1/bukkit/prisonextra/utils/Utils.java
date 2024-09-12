package xyz.sk1.bukkit.prisonextra.utils;

import org.bukkit.ChatColor;

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
