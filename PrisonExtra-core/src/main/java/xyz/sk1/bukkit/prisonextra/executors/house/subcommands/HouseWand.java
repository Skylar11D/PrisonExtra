package xyz.sk1.bukkit.prisonextra.executors.house.subcommands;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;
import xyz.sk1.bukkit.prisonextra.utils.builder.ItemStackBuilder;

public class HouseWand implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        ItemStack wand = new ItemStackBuilder(Material.WOOD_AXE)
                .setEnchanted(true)
                .setName(Utils.colorize("&c&lWand"))
                .setLore("&7Left and right click to mark", "the corners of the house")
                .build();

        sender.playSound(sender.getLocation(), Sound.LEVEL_UP, 15f, 15f);

        sender.getInventory().addItem(wand);

    }
}
