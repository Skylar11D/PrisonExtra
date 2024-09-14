package xyz.sk1.bukkit.prisonextra.internal.configuration.type;

import xyz.sk1.bukkit.prisonextra.manager.Manager;

public interface ConfigurationManager extends Manager {

    default void setDefaultOptions() {}

    void setOption(String option, Object value);

}
