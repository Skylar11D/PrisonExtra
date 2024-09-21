package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.UUID;

public class PrisonNPC extends NPC {

    public PrisonNPC(String name, Location location) {
        super(name, location);
        customize();
    }

    public PrisonNPC(String name, Location location, String texture, String signature) {
        super(name, location, texture, signature);
        customize();
    }

    @Override
    protected void customize() {

        WorldServer serverWorld = ((CraftWorld)getPosition().getWorld()).getHandle();

        MinecraftServer minecraftServer = ((CraftServer)Bukkit.getServer()).getServer();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), getName());

        if(super.getSignature() != null && super.getTexture() != null)
            gameProfile.getProperties().put("textures", new Property("textures", super.getTexture(), super.getSignature()));

        Utils.LOG.info("{DEBUG] profile: "+gameProfile.getProperties().toString()+" (the new)");

        PlayerInteractManager interactManager = new PlayerInteractManager(serverWorld);

        super.setNpc(new EntityPlayer(minecraftServer, serverWorld, gameProfile, interactManager));
        super.setId(getNpc().getId());

        //DataWatcher watcher = getNpc().getDataWatcher();
        //watcher.register(new DataWatcherObject<>(10, DataWatcherRegistry.a), (byte) (0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40));
       // watcher.watch(0, (byte) (0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40));

        DataWatcher dw = new DataWatcher(null);
        dw.a(10, (byte) (0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40));
        PacketPlayOutEntityMetadata metaPacket = new PacketPlayOutEntityMetadata(getNpc().getId(), dw, true);

        super.getNpc().setPosition(getPosition().getX(), getPosition().getY(), getPosition().getZ());

        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, super.getNpc());

        PacketPlayOutNamedEntitySpawn spawnEntity = new PacketPlayOutNamedEntitySpawn(getNpc());

        PacketPlayOutEntityDestroy entityRemoval = new PacketPlayOutEntityDestroy(getId());

        super.infoPacket = info;
        super.spawnPacket = spawnEntity;
        super.metadata = metaPacket;
        super.destroy = entityRemoval;


    }

    @Override
    protected NonPlayerType type() {
        return NonPlayerType.MANAGEMENT;
    }
}
