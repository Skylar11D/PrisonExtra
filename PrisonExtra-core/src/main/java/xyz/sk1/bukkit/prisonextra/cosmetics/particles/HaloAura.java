package xyz.sk1.bukkit.prisonextra.cosmetics.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.internal.perks.Perk;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class HaloAura implements Perk {

    private BukkitTask runnable;

    @Override
    public void activate(Player player) {
        Utils.LOG.info("Outer activated");

        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Utils.LOG.info("Inner activated");
                Location location = player.getLocation().add(0, 1, 0); // Adjust the height as needed
                int numParticles = 36;
                double radius = 0.5;

                for (int i = 0; i < numParticles; i++) {
                    double angle = (2 * Math.PI * i) / numParticles;
                    Vector vector = new Vector(Math.cos(angle) * radius, 0, Math.sin(angle) * radius);
                    location.getWorld().playEffect(location.add(vector), Effect.FLAME,1, 0);
                    location.subtract(vector);
                }
            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);

    }

    @Override
    public void deactivate() {
        if(runnable != null)
            this.runnable.cancel();
    }
}
