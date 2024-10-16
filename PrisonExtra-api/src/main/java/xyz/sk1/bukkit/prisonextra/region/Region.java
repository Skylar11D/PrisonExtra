package xyz.sk1.bukkit.prisonextra.region;

import lombok.Data;
import lombok.Setter;
import org.bukkit.Location;

@Data
public abstract class Region {

    private final double minX;
    private final double minY;
    private final double minZ;
    private final double maxX;
    private final double maxY;
    private final double maxZ;

    private final String name;

    @Setter
    private RegionState state;

    protected Region(Location position1, Location position2, String name, RegionState state) {
        this.name = name;
        this.state = state;

        this.minX = Math.min(position1.getX(), position2.getX());
        this.minY = Math.min(position1.getY(), position2.getY());
        this.minZ = Math.min(position1.getZ(), position2.getZ());
        this.maxX = Math.max(position1.getX(), position2.getX());
        this.maxY = Math.max(position1.getY(), position2.getY());
        this.maxZ = Math.max(position1.getZ(), position2.getZ());
    }

    public abstract boolean contains(Location location);

}
