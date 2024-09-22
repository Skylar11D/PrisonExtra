package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;

public abstract class User implements Prisoner, NpcObserver {

    @Override
    public void displayNPC(NPC npc) {

        FakePlayerManager npcManager = (FakePlayerManager) Core.getInstance().
                getManagerRegistry().getManager(ManagerType.NPC);

        npcManager.showNpcTo(getPlayer(), npc);

    }

    @Override
    public void terminateNPC(NPC npc) {
        ((CraftPlayer)getPlayer()).getHandle().playerConnection.sendPacket(npc.getDestroy());
    }

    @Override
    public void openMainMenu(){

        getPlayer().openInventory(Core.getInstance().getMenuFactory().createMenu(MenuType.MAIN));
        getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP, 15f, 15f);

    }

}
