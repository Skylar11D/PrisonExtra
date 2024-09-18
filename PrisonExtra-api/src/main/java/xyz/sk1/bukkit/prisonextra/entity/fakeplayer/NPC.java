package xyz.sk1.bukkit.prisonextra.entity.fakeplayer;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.type.NonPlayerType;

public abstract class NPC {

    private Player creator;
    @Getter
    private EntityPlayer npc;
    @Getter
    private String name;

    private String texture;
    private String signature;

    private Location position;

    @Getter
    protected PacketPlayOutPlayerInfo infoPacket;
    @Getter
    protected PacketPlayOutSpawnEntity spawnPacket;

    @Getter @Setter
    private int id;

    public NPC(String name, Location location, Player player){
        this.name = name;
        this.position = location;
        this.creator = player;

    }

    public NPC(String name, Location location, Player player, String texture, String signature){
        this(name, location, player);
        this.texture = texture;
        this.signature = signature;
    }

    protected abstract void customize();
    protected abstract NonPlayerType type();

}
