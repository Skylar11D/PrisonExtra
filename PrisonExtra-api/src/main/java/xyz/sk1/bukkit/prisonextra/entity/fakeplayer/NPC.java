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
    @Setter
    private EntityPlayer npc;
    @Getter
    private String name;

    @Getter
    private String texture;
    @Getter
    private String signature;

    @Getter
    private Location position;

    @Getter
    protected PacketPlayOutPlayerInfo infoPacket;
    @Getter
    protected PacketPlayOutNamedEntitySpawn spawnPacket;
    @Getter
    protected PacketPlayOutEntityMetadata metadata;
    @Getter
    protected PacketPlayOutEntityDestroy destroy;

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
