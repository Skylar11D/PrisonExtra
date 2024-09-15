package xyz.sk1.bukkit.prisonextra.internal.configuration.factory;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.sk1.bukkit.prisonextra.Base;
import xyz.sk1.bukkit.prisonextra.utilities.factory.AbstractConfigurationFactory;

import java.io.File;

public class ConfigurationFactory extends AbstractConfigurationFactory {

    @Override
    public FileConfiguration createConfig(Base plugin, String name) {
        File file = new File(plugin.getDataFolder(), name);

        if(!file.exists())
            plugin.saveResource(name, false);

        return YamlConfiguration.loadConfiguration(file);
    }

}
