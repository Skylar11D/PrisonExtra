package xyz.sk1.bukkit.prisonextra.internal.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.sk1.bukkit.prisonextra.Core;

import java.io.File;
import java.io.IOException;

public class YamlConfigurationHandler implements ConfigurationHandler<FileConfiguration> {

    private final File file;
    private FileConfiguration fileConfiguration;

    public YamlConfigurationHandler(File file) {
        this.file = file;
        load();
    }

    @Override
    public void load() {
        if(!file.exists()){
            Core.getInstance().saveResource(file.getName(), false);
            this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void save() {
        try {
            this.fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileConfiguration get() {
        return fileConfiguration;
    }

}
