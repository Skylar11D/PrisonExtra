package xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory;

import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;

public class NPCFactory {

    public NPC createPlain(String name, String texture, String signature, Location location){

        return new PrisonNPC(name, location, texture, signature);
    }

    public NPC createTextured(String name, String texture, String signature, Location location){

        return new PrisonNPC(name, location, texture, signature);
    }

}
