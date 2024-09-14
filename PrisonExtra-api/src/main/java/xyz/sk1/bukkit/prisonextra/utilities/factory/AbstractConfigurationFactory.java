package xyz.sk1.bukkit.prisonextra.utilities.factory;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.sk1.bukkit.prisonextra.Base;

public abstract class AbstractConfigurationFactory {

    public abstract FileConfiguration createConfig(Base plugin, String name);

}
