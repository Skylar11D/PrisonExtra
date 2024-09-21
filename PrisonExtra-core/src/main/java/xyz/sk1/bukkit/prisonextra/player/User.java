package xyz.sk1.bukkit.prisonextra.player;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NpcObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
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
    public void openMenu(){

        Gui gui = Gui.gui()
                .title(
                        Component.text("Prison menu")
                                .decorate(TextDecoration.BOLD)
                                .color(NamedTextColor.RED)
                )
                .type(GuiType.CHEST)
                .rows(6)
                .create();

        GuiItem cosmetics = ItemBuilder.from(Material.BLAZE_POWDER)
                .name(
                        Component.text("Cosmetics")
                                .color(NamedTextColor.AQUA)
                                .decorate(TextDecoration.BOLD)

                )
                .lore(
                        Component.text(""),
                        Component.text("Fetch yourself some magical particles")
                                .color(NamedTextColor.DARK_AQUA),
                        Component.text("and brag about them among prisoners!")
                                .color(NamedTextColor.GREEN)
                )
                .asGuiItem(action -> {

        });

        GuiItem house = ItemBuilder.from(Material.DARK_OAK_DOOR)
                .name(
                        Component.text("Housing")
                                .color(NamedTextColor.DARK_GREEN)
                                .decorate(TextDecoration.BOLD)
                )
                .lore(
                        Component.text(" "),
                        Component.text("Discover and rent a house for you now!")
                                .color(NamedTextColor.GREEN),
                        Component.text("DISCOUNT 50%!")
                                .color(NamedTextColor.GREEN)
                )
                .asGuiItem(action -> {

        });

        GuiItem minions = ItemBuilder.from(Material.STONE_PICKAXE)
                .name(
                        Component.text("Minions")
                                .color(NamedTextColor.YELLOW)
                                .decorate(TextDecoration.BOLD)
                )
                .lore(
                        Component.text(" "),
                        Component.text("Your personal friendly minion that will")
                                .color(NamedTextColor.GOLD),
                        Component.text("catch your back even in harsh environments!")
                                .color(NamedTextColor.GOLD),
                        Component.text("look for your favorite type and purchase or even rent it!")
                                .color(NamedTextColor.GOLD)
                )
                .asGuiItem(action -> {

        });

        gui.getFiller().fill(ItemBuilder.from(Material.STAINED_GLASS_PANE).asGuiItem());

        gui.setItem(20, house);
        gui.setItem(22, minions);
        gui.setItem(24, cosmetics);

        gui.setDefaultClickAction(action -> action.setCancelled(true));

        getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP, 15f, 15f);

        gui.open(getPlayer());

    }

}
