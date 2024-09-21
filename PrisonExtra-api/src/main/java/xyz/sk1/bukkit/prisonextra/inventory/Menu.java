package xyz.sk1.bukkit.prisonextra.inventory;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu extends CraftInventoryCustom {

    public Menu(InventoryHolder owner, InventoryType type, String title) {
        super(owner, type, title);
    }

}
