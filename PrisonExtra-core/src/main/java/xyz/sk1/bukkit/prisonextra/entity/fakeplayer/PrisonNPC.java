package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PrisonNPC extends NPC {

    private EntityPlayer npc;

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

        gameProfile.getProperties().put("textures", new Property("textures", getTexture(), getSignature()));

        PlayerInteractManager interactManager = new PlayerInteractManager(serverWorld);

        this.npc = new EntityPlayer(minecraftServer, serverWorld, gameProfile, interactManager);
        setId(npc.getBukkitEntity().getEntityId());

        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc);

        PacketPlayOutSpawnEntity spawnEntity = new PacketPlayOutSpawnEntity(npc, getId());

        this.npc.setPosition(getPosition().getX(), getPosition().getY(), getPosition().getZ());

        this.infoPacket = info;
        this.spawnPacket = spawnEntity;

    }

    @Override
    protected NonPlayerType type() {
        return NonPlayerType.MANAGEMENT;
    }
}
