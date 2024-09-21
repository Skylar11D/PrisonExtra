package xyz.sk1.bukkit.prisonextra.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utils.builder.ItemStackBuilder;

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
    public void openMenu(){

        Inventory inventory = Bukkit.createInventory(null, 54, "              §c§lPrison");

        ItemStack background = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7)
                .setName("")
                .setEnchanted(true)
                .build();

        ItemStack house = new ItemStackBuilder(Material.WOOD_DOOR)
                .setName("§a§lHouse")
                .setLore("",
                        "§2Look up for a good safe place for rent or on sale",
                        "§2and even sell your items in it!"
                )
                .build();

        ItemStack minion = new ItemStackBuilder(Material.STONE_PICKAXE)
                .setName("§e§lMinion")
                .setLore("",
                        "§6Your personal friendly minion that mines for you!",
                        "§6Purchase or rent one for your favor"
                )
                .build();

        ItemStack cosmetics = new ItemStackBuilder(Material.BLAZE_POWDER)
                .setName("§d§lCosmetics")
                .setLore("",
                        "§5Bring some particles around you and brag",
                        "§5about them among other prisoners!"
                )
                .build();



        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, background);

            if(i == 20)
                inventory.setItem(i, house);

            if(i == 22)
                inventory.setItem(i, minion);

            if(i == 24)
                inventory.setItem(i, cosmetics);

        }

        getPlayer().openInventory(inventory);
        getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP, 15f, 15f);

    }

}
