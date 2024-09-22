package xyz.sk1.bukkit.prisonextra.menu;

import xyz.sk1.bukkit.prisonextra.inventory.Menu;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.inventory.PrisonMenu;

public class MenuFactory {

    public Menu createMenu(MenuType type){

        switch (type){
            case MAIN: {
                return new PrisonMenu(54, "              §c§lPrison");
            }

            /*case MINIONS: {
                return null;
            }

            case COSMETICS: {
                return null;
            }*/

        }

        return null;

    }

}
