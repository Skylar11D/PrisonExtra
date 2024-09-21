package xyz.sk1.bukkit.prisonextra.events.handler.interact;

import com.comphenix.protocol.events.PacketEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;

public class NpcInteractHandler {

    public void handle(PacketEvent event) {
        UserManager userManager = (UserManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        User user = userManager.get(event.getPlayer());


        Utils.LOG.info("interact handled!");

        CompletableFuture.runAsync(() -> {
            user.openMenu();
        });

    }
}
