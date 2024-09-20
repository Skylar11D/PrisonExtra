package xyz.sk1.bukkit.prisonextra.executors.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcCreate;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcRemove;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.UUID;

@Attributes(name = "npc", requiresPlayer = true, permission = "marsmc.admin")
public class NPCCommand extends Executor {

    private final SubNpcCreate subNpcCreate;
    private final SubNpcRemove subNpcRemove;

    public NPCCommand(){
        this.subNpcCreate = new SubNpcCreate();
        this.subNpcRemove = new SubNpcRemove();
    }

    @Override
    protected void execute(Player sender, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Utils.colorize("&cSyntax: /npc create <name> type <general|null|null>"));
            sender.sendMessage(Utils.colorize("&cSyntax: /npc remove <name>"));
            return;
        }

        switch (args[0]){
            case "create": {
                subNpcCreate.dispatch(sender, args);
                break;
            }

            case "remove": {
                subNpcRemove.dispatch(sender, args);
                break;
            }

            default: {
                sender.sendMessage(Utils.colorize("&cInvalid first arguments!"));
                break;
            }
        }

    }
}
