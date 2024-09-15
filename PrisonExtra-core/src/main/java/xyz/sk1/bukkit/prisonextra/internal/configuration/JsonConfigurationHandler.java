package xyz.sk1.bukkit.prisonextra.internal.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.sk1.bukkit.prisonextra.Base;

import java.io.File;

@Getter
@RequiredArgsConstructor
public class JsonConfigurationHandler implements ConfigurationHandler {

    private final File file;

    @Override
    public void load() {

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
