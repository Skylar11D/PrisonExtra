package xyz.sk1.bukkit.prisonextra.internal.configuration;

public interface ConfigurationHandler<D> {

    void load();

    void save();

    D get(String path);

    void set(String path, D victim);

}
