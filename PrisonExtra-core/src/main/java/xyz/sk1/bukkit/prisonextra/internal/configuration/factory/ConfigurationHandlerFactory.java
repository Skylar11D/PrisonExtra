package xyz.sk1.bukkit.prisonextra.internal.configuration.factory;

import xyz.sk1.bukkit.prisonextra.Base;
import xyz.sk1.bukkit.prisonextra.internal.configuration.ConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.JsonConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;

import java.io.File;
import java.util.Optional;

public class ConfigurationHandlerFactory {

    public Optional<ConfigurationHandler> createConfigHandler(File name) {

        if(name.getName().endsWith(".yml") || name.getName().endsWith(".yaml")) {
            return Optional.of(new YamlConfigurationHandler(name));
        }

        else if (name.getName().endsWith(".json")) {
            return Optional.of(new JsonConfigurationHandler(name));
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + name);
        }

    }

}
