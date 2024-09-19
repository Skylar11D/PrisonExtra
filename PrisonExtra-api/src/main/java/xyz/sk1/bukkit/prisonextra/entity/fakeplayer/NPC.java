package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;

public abstract class NPC {

    @Getter
    private EntityPlayer npc;
    @Getter(AccessLevel.PROTECTED)
    private String name;

    @Getter(AccessLevel.PROTECTED)
    private String texture;
    @Getter(AccessLevel.PROTECTED)
    private String signature;

    @Getter(AccessLevel.PROTECTED)
    private Location position;

    @Getter
    protected PacketPlayOutPlayerInfo infoPacket;
    @Getter
    protected PacketPlayOutSpawnEntity spawnPacket;

    @Getter @Setter
    private int id;

    public NPC(String name, Location location){
        this.name = name;
        this.position = location;

    }

    public NPC(String name, Location location, String texture, String signature){
        this(name, location);
        this.texture = texture;
        this.signature = signature;
    }

    protected abstract void customize();
    protected abstract NonPlayerType type();

}
