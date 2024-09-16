package xyz.sk1.bukkit.prisonextra.menu;

import io.github.mqzen.menus.base.Content;
import io.github.mqzen.menus.base.Menu;
import io.github.mqzen.menus.misc.Capacity;
import io.github.mqzen.menus.misc.DataRegistry;
import io.github.mqzen.menus.titles.MenuTitle;
import io.github.mqzen.menus.titles.MenuTitles;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class ShopMenu implements Menu {
    @Override
    public String getName() {
        return "ShopName";
    }

    @Override
    public @NotNull MenuTitle getTitle(DataRegistry dataRegistry, Player player) {
        return MenuTitles.createLegacy(Utils.COLORIZE("&cExampleMenu"));
    }

    @Override
    public @NotNull Capacity getCapacity(DataRegistry dataRegistry, Player player) {
        return null;
    }

    @Override
    public @NotNull Content getContent(DataRegistry dataRegistry, Player player, Capacity capacity) {
        return null;
    }
}
