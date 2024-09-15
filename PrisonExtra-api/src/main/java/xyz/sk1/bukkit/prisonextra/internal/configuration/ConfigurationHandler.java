package xyz.sk1.bukkit.prisonextra.internal.configuration;

public interface ConfigurationHandler {

    void load();

    void save();

    Object get();

    void set();

}
