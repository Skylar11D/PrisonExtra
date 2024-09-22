package xyz.sk1.bukkit.prisonextra.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.sk1.bukkit.prisonextra.events.handler.inventory.CosmeticsHandler;
import xyz.sk1.bukkit.prisonextra.events.handler.inventory.HousingHandler;
import xyz.sk1.bukkit.prisonextra.events.handler.inventory.MinionHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class InventoryClickListener extends BaseListener {

    private final PrisonEventHandler<InventoryClickEvent> minionHandler, housingHandler, cosmeticsHandler;


    public InventoryClickListener(){
        this.minionHandler = new MinionHandler();
        this.housingHandler = new HousingHandler();
        this.cosmeticsHandler = new CosmeticsHandler();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        ItemStack itemStack = event.getCurrentItem();

        if(!Utils.hasTag(itemStack, "menu"))
            return;

        if(Utils.getTag(itemStack, "menu") == "background")
            event.setCancelled(true);

        if (Utils.getTag(itemStack, "menu") == "minion"){
            event.setCancelled(true);
            minionHandler.handle(event);
        }

        if (Utils.getTag(itemStack, "menu") == "housing"){
            event.setCancelled(true);
            housingHandler.handle(event);
        }

        if (Utils.getTag(itemStack, "menu") == "cosmetics"){
            event.setCancelled(true);
            cosmeticsHandler.handle(event);
        }

    }

}
