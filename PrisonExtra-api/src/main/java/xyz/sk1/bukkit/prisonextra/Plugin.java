package xyz.sk1.bukkit.prisonextra;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Plugin extends JavaPlugin implements Extra {

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        fini();
    }
}
