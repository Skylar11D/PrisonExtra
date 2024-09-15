package xyz.sk1.bukkit.prisonextra.internal.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.sk1.bukkit.prisonextra.Base;
import xyz.sk1.bukkit.prisonextra.Core;

import java.io.File;

@Getter
@RequiredArgsConstructor
public class YamlConfigurationHandler implements ConfigurationHandler {

    private final File file;

    @Override
    public void load() {
        if(!file.exists()){
            Core.getInstance().saveResource(file.getName(), true);
        }
    }

    @Override
    public void save() {
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public void set() {

    }

}
