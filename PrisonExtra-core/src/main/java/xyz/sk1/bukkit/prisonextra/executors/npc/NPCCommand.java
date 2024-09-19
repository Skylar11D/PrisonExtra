package xyz.sk1.bukkit.prisonextra.executors.npc;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcCreate;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcRemove;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

@Attributes(name = "npc", requiresPlayer = true, permission = "marsmc.admin")
public class NPCCommand extends Executor {

    private SubNpcCreate subNpcCreate;
    private SubNpcRemove subNpcRemove;

    private NPCCommand(){
        this.subNpcCreate = new SubNpcCreate();
        this.subNpcRemove = new SubNpcRemove();
    }

    @Override
    protected void execute(Player sender, String[] args) {
        if(args.length < 1 || args.length > 4) {
            sender.sendMessage(Utils.colorize("&cSyntax: /npc create <name> type <general|null|null>"));
            sender.sendMessage(Utils.colorize("&cSyntax: /npc remove <name>"));
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
