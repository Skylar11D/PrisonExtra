package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;

public abstract class User implements Prisoner, NpcObserver {

    @Override
    public void displayNPC(NPC npc) {
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(npc.getInfoPacket());
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(npc.getSpawnPacket());
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(npc.getMetadata());
        getPlayer().sendMessage("notified!");
        getPlayer().sendMessage("name npc: " +npc.getNpc().displayName);
        getPlayer().sendMessage("x:"+npc.getNpc().locX);
        getPlayer().sendMessage("y:"+npc.getNpc().locY);getPlayer().sendMessage("z:"+npc.getNpc().locZ);

    }
}
