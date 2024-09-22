package xyz.sk1.bukkit.prisonextra.executors.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcCreate;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcRemove;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcStareMode;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.Locale;
import java.util.UUID;

@Attributes(name = "npc", requiresPlayer = true, permission = "marsmc.admin")
public class NPCCommand extends Executor {

    private final Subcommand subNpcCreate, subNpcRemove, subNpcStareMode;

    public NPCCommand(){
        this.subNpcCreate = new SubNpcCreate();
        this.subNpcRemove = new SubNpcRemove();
        this.subNpcStareMode = new SubNpcStareMode();
    }

    @Override
    protected void execute(Player sender, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Utils.colorize("&cSyntax: /npc create <name> type <general|null|null>"));
            sender.sendMessage(Utils.colorize("&cSyntax: /npc remove <name>"));
            sender.sendMessage(Utils.colorize("&cSyntax: /npc stare <true/false>"));
            return;
        }

        switch (args[0].toLowerCase()){
            case "create": {
                subNpcCreate.dispatch(sender, args);
                break;
            }

            case "remove": {
                subNpcRemove.dispatch(sender, args);
                break;
            }

            case "stare": {
                subNpcStareMode.dispatch(sender, args);
                break;
            }

            default: {
                sender.sendMessage(Utils.colorize("&cInvalid first arguments!"));
                break;
            }
        }

    }
}
