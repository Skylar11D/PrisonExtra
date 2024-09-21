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
import org.bukkit.event.player.PlayerInteractEntityEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;

import java.lang.reflect.InvocationTargetException;

public class NpcInteractHandler implements PrisonEventHandler<PlayerInteractEntityEvent> {

    @Override
    public void handle(PlayerInteractEntityEvent event) {
        UserManager userManager = (UserManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        User user = userManager.get(event.getPlayer());

        user.openMenu();

    }
}
