package xyz.sk1.bukkit.prisonextra.cosmetics.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.internal.perks.Perk;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class HaloAura extends Perk {

    @Override
    public void activate(Player player) {
        Utils.LOG.info("Outer activated");

        super.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Utils.LOG.info("Inner activated");

                Location loc = player.getLocation().clone(); // Clone the location to avoid modifying the original
                loc.setY(loc.getY() + 2); // Set the Y location above the player (2 blocks above)

                double radius = 0.5; // Radius of the circle
                int particleCount = 20; // Number of particles in the circle

                // Spawn particles in a circle
                for (int i = 0; i < particleCount; i++) {
                    double angle = 2 * Math.PI / particleCount * i; // Calculate the angle for each particle

                    // Calculate the X and Z position for the particle based on the angle
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);

                    // Set the particle location
                    loc.add(x, 0, z); // Move to the correct position
                    player.getWorld().playEffect(loc, Effect.LAVADRIP, 1); // Change particle type as needed
                    loc.subtract(x, 0, z); // Reset location after spawning
                }
                
            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);

    }

}
