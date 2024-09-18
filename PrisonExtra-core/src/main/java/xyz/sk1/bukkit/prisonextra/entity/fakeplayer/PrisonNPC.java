package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PrisonNPC extends NPC {

    private Player creator;
    private String name;
    private String texture;
    private String signature;
    private EntityPlayer npc;

    public PrisonNPC(String name, Location location, Player player, String texture, String signature) {
        super(name, location, player, texture, signature);
        customize();
    }

    @Override
    protected void customize() {
        EntityPlayer serverPlayer = ((CraftPlayer)creator).getHandle();

        WorldServer serverWorld = serverPlayer.world.getWorld().getHandle();

        MinecraftServer minecraftServer = serverPlayer.server;

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);

        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));

        PlayerInteractManager interactManager = new DemoPlayerInteractManager(serverWorld);

        this.npc = new EntityPlayer(minecraftServer, serverWorld, gameProfile, interactManager);
        setId(npc.getId());

        PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc);

        PacketPlayOutSpawnEntity spawnEntity = new PacketPlayOutSpawnEntity(npc, npc.getId());

        this.infoPacket = info;
        this.spawnPacket = spawnEntity;

        CompletableFuture.runAsync(() -> {
            for (Player player : Bukkit.getOnlinePlayers()){
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(info);
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(spawnEntity);
            }
        });
    }

    @Override
    protected NonPlayerType type() {
        return NonPlayerType.MANAGEMENT;
    }
}
