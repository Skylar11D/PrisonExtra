package xyz.sk1.bukkit.prisonextra.inventory;

import xyz.sk1.bukkit.prisonextra.inventory.menus.CosmeticsMenu;
import xyz.sk1.bukkit.prisonextra.inventory.menus.HousingMenu;
import xyz.sk1.bukkit.prisonextra.inventory.menus.MinionMenu;
import xyz.sk1.bukkit.prisonextra.inventory.menus.PrisonMenu;

public class MenuFactory {

    public Menu createMenu(MenuType type){

        switch (type){
            case MAIN: {
                return new PrisonMenu(54, "               §c§lPrison");
            }

            case MINIONS: {
                return new MinionMenu(27, "               §e§lMinion");
            }

            case COSMETICS: {
                return new CosmeticsMenu(54, "            §d§lCosmetics");
            }

            case HOUSING: {
                return new HousingMenu(54, "               §a§lHousing");
            }

        }

        return null;

    }

}
