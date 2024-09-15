package xyz.sk1.bukkit.prisonextra.internal.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;

public class SettingsManager implements Settings {

    private final FileConfiguration fileConfiguration;

    public SettingsManager(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public void setOption(String option, Object value) {



    }

    @Override
    public void load() throws Exception {



    }

    @Override
    public ManagerType getType() {
        return ManagerType.CONFIGURATION;
    }
}
