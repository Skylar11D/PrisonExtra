package xyz.sk1.bukkit.prisonextra.inventory;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public abstract class Menu extends CraftInventoryCustom {

    private final Set<Integer> exceptionSlots = new HashSet<>();;
    protected final Map<Integer, ItemStack> strictedMap = new HashMap<>();

    public Menu(int size, String title) {
        super(null, size, title);
    }


    public void fill(ItemStack item){

        for (int i = 0; i < getSize(); i++) {
            super.setItem(i, item);
        }

    }

    public void fillExcept(ItemStack item, int... exceptionSlot){

        exceptionSlots.addAll(exceptionSlots);

        for (int i = 0; i < getSize(); i++) {

            if(exceptionSlots.contains(i))
                continue;

            super.setItem(i, item);
        }

    }

    public void fillStrict(ItemStack item, Map<Integer, ItemStack> items){

        for (int i = 0; i < getSize(); i++) {

            if(items.containsKey(i)){
                super.setItem(i, items.get(i));
                continue;
            }

            super.setItem(i, item);

        }

    }


}
