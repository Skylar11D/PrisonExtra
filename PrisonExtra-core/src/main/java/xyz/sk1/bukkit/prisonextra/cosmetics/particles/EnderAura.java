package xyz.sk1.bukkit.prisonextra.cosmetics.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.perks.Perk;

public class EnderAura extends Perk {

    private BukkitTask runnable;

    @Override
    public void activate(Player player) {

        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = player.getLocation().clone(); // Clone the location to avoid modifying the original
                loc.setY(loc.getY() + 1.5); // Set the Y location above the player

                double a = 1.5; // Horizontal radius (along the X-axis)
                double b = 0.75; // Vertical radius (along the Z-axis)
                int particleCount = 30; // Number of particles in the aura

                // Spawn particles in an oval motion to simulate an aura
                for (int i = 0; i < particleCount; i++) {
                    double angle = 2 * Math.PI / particleCount * i; // Calculate the angle for each particle

                    // Calculate the X and Z position for the particle based on the angle
                    double x = a * Math.cos(angle); // Horizontal radius affecting X
                    double z = b * Math.sin(angle); // Vertical radius affecting Z

                    // Set the particle location
                    loc.add(x, 0, z); // Move to the correct position
                    player.getWorld().playEffect(loc, Effect.PORTAL, 1); // Use Particle.PORTAL to represent the aura
                    loc.subtract(x, 0, z); // Reset location after spawning
                }

            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);

    }

}
