package xyz.sk1.bukkit.prisonextra.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.utils.builder.ItemStackBuilder;

import java.util.HashMap;
import java.util.Map;

public class PrisonMenu extends Menu {

    private final Map<Integer, ItemStack> MAP = new HashMap<>();

    public PrisonMenu(int size, String title) {
        super(size, title);

        ItemStack background = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7)
                .setName("")
                .setEnchanted(true)
                .build();

        fillStrict(background, content());
    }

    private Map<Integer, ItemStack> content(){

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

        MAP.put(20, house);
        MAP.put(22, minion);
        MAP.put(24, cosmetics);

        return MAP;
    }

}