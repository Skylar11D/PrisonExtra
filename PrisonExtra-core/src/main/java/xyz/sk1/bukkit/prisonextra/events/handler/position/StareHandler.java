package xyz.sk1.bukkit.prisonextra.events.handler.position;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import lombok.var;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.lang.reflect.InvocationTargetException;

public class StareHandler implements PrisonEventHandler<PlayerMoveEvent> {

    @Override
    public void handle(PlayerMoveEvent event) {
        Core core = Core.getInstance();
        FakePlayerManager fakePlayerManager = (FakePlayerManager) core.getManagerRegistry().getType(ManagerType.NPC);
        FileConfiguration configuration = ((YamlConfigurationHandler)core.getSettings()).get();
        boolean should = configuration.getBoolean("entities.npcs.global.stare");

        if(!should)
            return;

        ProtocolManager networkManager = core.getProtocolManager();
        Player player = event.getPlayer();
        Location pLoc = player.getLocation();

        fakePlayerManager.getCache().values().forEach(npc -> {
            Location location = npc.getHandle().getBukkitEntity().getLocation();
            double df = pLoc.distanceSquared(location); //how is the obfuscation? lol
            Utils.LOG.info("[DEBUG] distance = " + df);

            if(df > 55.0)
                return;

            location.setDirection(pLoc.subtract(location).toVector());
            float yaw = location.getYaw();
            float PITCH = location.getPitch();

            PacketContainer container = networkManager.createPacket(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
            //PacketContainer container1 = networkManager.createPacket(PacketType.Play.Server.ENTITY_LOOK);

            container.getIntegers().write(0, npc.getId());
            container.getBytes().write(0, (byte)(yaw*270.0f/360.0f));

            /*container1.getIntegers().write(0, npc.getId());
            container1.getBytes().write(0, (byte)((yaw%360)*256/360));
            container1.getBytes().write(1, (byte)((PITCH%360)*256/360));*/

            try {
                    networkManager.sendServerPacket(player, container);
                    //networkManager.sendServerPacket(player, container1);
                    var t = new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) (yaw*276.0f/360.0f), (byte) (PITCH*276.0f/360.0f),true);
                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(t);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        });

    }

}
