package xyz.sk1.bukkit.prisonextra.region;

import lombok.Data;

@Data
public abstract class Region {

    private final int xMin;
    private final int yMin;
    private final int zMin;
    private final int xMax;
    private final int yMax;
    private final int zMax;

}
