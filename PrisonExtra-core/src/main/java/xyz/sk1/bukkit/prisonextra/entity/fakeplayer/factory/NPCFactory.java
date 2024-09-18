package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;

public class NPCFactory {

    public NPC createNPC(String name, String texture, String signature, Player player, Location location){

        return new PrisonNPC(name, location, player, texture, signature);
    }

}
