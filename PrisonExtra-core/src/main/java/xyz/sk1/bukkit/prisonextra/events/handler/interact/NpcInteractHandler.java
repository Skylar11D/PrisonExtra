package xyz.sk1.bukkit.prisonextra.events.handler.interact;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.lang.reflect.InvocationTargetException;

public class NpcInteractHandler implements PrisonEventHandler<PlayerInteractAtEntityEvent> {

    @Override
    public void handle(PlayerInteractAtEntityEvent event) {
        UserManager userManager = (UserManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        //User user = userManager.get(event.getEntity().);

        Utils.LOG.info("interact handled!");

        //user.openMenu();

    }
}
