package xyz.sk1.bukkit.prisonextra.inventory.menus;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.inventory.Menu;
import xyz.sk1.bukkit.prisonextra.utils.builder.ItemStackBuilder;

import java.util.Map;

public class MinionMenu extends Menu {

    public MinionMenu(int size, String title) {
        super(size, title);


    }

    private Map<Integer, ItemStack> content(){

        ItemStack housing = new ItemStackBuilder(Material.WOOD_DOOR)
                .setName("§a§lHousing")
                .setMetaData("menu", "housing")
                .setLore("",
                        "§2Look up for a good safe place for rent or on sale",
                        "§2and even sell your items in it!"
                )
                .build();

        ItemStack minion = new ItemStackBuilder(Material.STONE_PICKAXE)
                .setName("§e§lMinion")
                .setMetaData("menu", "minion")
                .setLore("",
                        "§6Your personal friendly minion that mines for you!",
                        "§6Purchase or rent one for your favor"
                )
                .build();

        ItemStack cosmetics = new ItemStackBuilder(Material.BLAZE_POWDER)
                .setName("§d§lCosmetics")
                .setMetaData("menu", "cosmetics")
                .setLore("",
                        "§5Bring some particles around you and brag",
                        "§5about them among other prisoners!"
                )
                .build();

        strictedMap.put(20, housing);
        strictedMap.put(22, minion);
        strictedMap.put(24, cosmetics);

        return strictedMap;
    }

}
