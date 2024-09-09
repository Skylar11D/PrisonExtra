package xyz.sk1.bukkit.prisonextra;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Base extends JavaPlugin implements ExtraPrison {

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        fini();
    }
}
