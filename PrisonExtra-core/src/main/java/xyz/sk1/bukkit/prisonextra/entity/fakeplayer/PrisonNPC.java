package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;

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

        if((getSignature() != null) && (getTexture() != null)){
            gameProfile.getProperties().put("textures", new Property("textures", getTexture(), getSignature()));
        }

        PlayerInteractManager interactManager = new PlayerInteractManager(serverWorld);

        super.setNpc(new EntityPlayer(minecraftServer, serverWorld, gameProfile, interactManager));
        setId(getNpc().getBukkitEntity().getEntityId());

        DataWatcher watcher = getNpc().getDataWatcher();
        //watcher.register(new DataWatcherObject<>(10, DataWatcherRegistry.a), (byte) (0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40));
       // watcher.watch(0, (byte) (0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40));

        super.getNpc().setLocation(getPosition().getX(), getPosition().getY(),
                getPosition().getZ(), getPosition().getYaw(), getPosition().getPitch());

        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, super.getNpc());

        PacketPlayOutNamedEntitySpawn spawnEntity = new PacketPlayOutNamedEntitySpawn(getNpc());

        super.infoPacket = info;
        super.spawnPacket = spawnEntity;
        super.metadata = new PacketPlayOutEntityMetadata(getNpc().getId(),watcher,false);

    }

    @Override
    protected NonPlayerType type() {
        return NonPlayerType.MANAGEMENT;
    }
}
