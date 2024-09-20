package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;

public abstract class User implements Prisoner, NPCObserver {

    @Override
    public void displayNPC(NPC npc) {
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(npc.getInfoPacket());
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(npc.getSpawnPacket());
        getPlayer().sendMessage("notified!");

        getPlayer().sendMessage("info packet: " +npc.getInfoPacket());
        getPlayer().sendMessage("spawn packet: " +npc.getSpawnPacket());

        getPlayer().sendMessage("id packet: " +npc.getId());
        getPlayer().sendMessage("name packet: " +npc.getNpc().displayName);
    }
}
