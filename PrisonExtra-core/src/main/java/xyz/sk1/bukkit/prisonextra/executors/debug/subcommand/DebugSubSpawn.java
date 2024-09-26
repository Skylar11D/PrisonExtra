package xyz.sk1.bukkit.prisonextra.executors.debug.subcommand;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.sk1.bukkit.prisonextra.entity.minion.Miner;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.entity.minion.factory.MinionFactory;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.utils.CustomEntityRegistry;

import java.lang.reflect.Field;
import java.util.Map;

public class DebugSubSpawn implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        if(!args[1].equalsIgnoreCase("miner")) {
            return;
        }

        MinionFactory minionFactory = new MinionFactory();
        Minion minion = minionFactory.createMinion(MinionType.MINER, sender.getLocation(), (Prisoner) sender).get();

        Utils.LOG.info("entity type: " + EntityTypes.b("Skeleton"));

    }
}
