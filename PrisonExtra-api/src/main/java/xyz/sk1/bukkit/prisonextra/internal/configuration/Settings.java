package xyz.sk1.bukkit.prisonextra.internal.configuration;

import xyz.sk1.bukkit.prisonextra.manager.Manager;

public interface Settings extends Manager {

    default void setDefaultOptions() {}

    void setOption(String option, Object value);

}