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
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Entity npc = event.getRightClicked();
        Location npcLoc = npc.getLocation();
        npcLoc.setDirection(location.subtract(npcLoc).toVector());
        float yaw = npcLoc.getYaw();
        float PITCH = npcLoc.getPitch();
        ProtocolManager manager = Core.getInstance().getProtocolManager();

        ((FakePlayerManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.NPC)).

        PacketContainer staringPacket = manager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
        PacketContainer bodyPacket = manager.createPacket(PacketType.Play.Server.ENTITY_LOOK);

        staringPacket.getIntegers().write(0, npc.getEntityId());
        staringPacket.getBytes().write(0, (byte)((yaw%360)*256/360));

        try {
            manager.sendServerPacket(player, staringPacket);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
