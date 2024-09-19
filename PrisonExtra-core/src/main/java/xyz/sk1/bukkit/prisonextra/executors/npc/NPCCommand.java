package xyz.sk1.bukkit.prisonextra.executors.npc;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.npc.subcommands.SubNpcCreate;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

@Attributes(name = "npc", requiresPlayer = true, permission = "marsmc.admin")
public class NPCCommand extends Executor {

    private SubNpcCreate subNpcCreate;

    private NPCCommand(){
        this.subNpcCreate = new SubNpcCreate();
    }

    @Override
    protected void execute(Player sender, String[] args) {
        if(args.length < 1 || args.length > 4)
            sender.sendMessage(Utils.colorize("&cSyntax: /npc create <name> type <general|null|null>"));

        subNpcCreate.dispatch(sender, args);

    }
}
