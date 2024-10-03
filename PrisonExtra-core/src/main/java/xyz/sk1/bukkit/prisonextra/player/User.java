package xyz.sk1.bukkit.prisonextra.player;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.cosmetics.particles.Butterfly;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.internal.perks.Perk;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

@Getter
@Setter
public abstract class User implements Prisoner, NpcObserver {

    private Location corner1;
    private Location corner2;

    private Perk activePerk = new Butterfly();

    @Override
    public void displayNPC(NPC npc) {

        FakePlayerManager npcManager = (FakePlayerManager) Core.getInstance().
                getManagerRegistry().getType(ManagerType.NPC);

        npcManager.showNpcTo(getHandle(), npc);

    }

    @Override
    public void terminateNPC(NPC npc) {
        ((CraftPlayer) getHandle()).getHandle().playerConnection.sendPacket(npc.getDestroy());
    }

    @Override
    public void openMenu(MenuType type){

        getHandle().openInventory(Core.getInstance().getMenuFactory().createMenu(type));
        getHandle().playSound(getHandle().getLocation(), Sound.LEVEL_UP, 15f, 15f);

    }

    @Override
    public void summonPerk(Perk perk) {

        removePerk();

        this.activePerk = perk;

        this.activePerk.activate(getHandle());
    }

    @Override
    public void removePerk() {
        this.activePerk.deactivate();
    }
}
