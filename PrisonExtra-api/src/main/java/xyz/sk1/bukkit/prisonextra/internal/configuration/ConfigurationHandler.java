package xyz.sk1.bukkit.prisonextra.internal.configuration;

public interface ConfigurationHandler<D> {

    void load();

    void save();

    default D get(String path){return null;}

    default void set(String path, D victim){}

}
