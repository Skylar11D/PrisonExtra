package xyz.sk1.bukkit.prisonextra.executors.npc;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcCreate;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcRemove;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcStareMode;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

@Attributes(name = "npc", requiresPlayer = true, permission = "marsmc.admin")
public class NpcCommand extends Executor {

    private final Subcommand subNpcCreate, subNpcRemove, subNpcStareMode;

    public NpcCommand(){
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
