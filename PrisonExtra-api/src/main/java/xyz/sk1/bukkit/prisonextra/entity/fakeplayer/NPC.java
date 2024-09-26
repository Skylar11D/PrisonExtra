package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;

@Getter
public abstract class NPC {

    @Setter()
    private EntityPlayer handle;
    private String name;

    private String texture;
    private String signature;

    private Location position;

    protected PacketPlayOutPlayerInfo infoPacket;
    protected PacketPlayOutNamedEntitySpawn spawnPacket;
    protected PacketPlayOutEntityMetadata metadata;
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
