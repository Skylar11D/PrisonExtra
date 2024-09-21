package xyz.sk1.bukkit.prisonextra.events.handler.position;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;

import java.lang.reflect.InvocationTargetException;

public class StareHandler implements PrisonEventHandler<PlayerMoveEvent> {

    @Override
    public void handle(PlayerMoveEvent event) {
        Core core = Core.getInstance();
        FakePlayerManager fakePlayerManager = (FakePlayerManager) core.getManagerRegistry().getManager(ManagerType.NPC);
        ProtocolManager networkManager = core.getProtocolManager();
        Player player = event.getPlayer();
        Location pLoc = player.getLocation();

        fakePlayerManager.getCache().values().forEach(npc -> {
            Location location = npc.getNpc().getBukkitEntity().getLocation();
            location.setDirection(pLoc.subtract(location).toVector());
            float yaw = location.getYaw();

            PacketContainer container = networkManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);

            container.getIntegers().write(0, npc.getId());
            container.getBytes().write(0, (byte)((yaw%360)*256/360));

            try {
                networkManager.sendServerPacket(player, container);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        });

    }

}
