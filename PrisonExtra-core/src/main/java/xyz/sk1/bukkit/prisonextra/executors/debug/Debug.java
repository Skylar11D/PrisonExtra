package xyz.sk1.bukkit.prisonextra.executors.debug;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.executors.debug.subcommand.DebugSubSpawn;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

@Attributes(name = "pdebug", requiresPlayer = true)
public class Debug extends Executor {

    private final Subcommand debugsuspawn;

    public Debug() {
        this.debugsuspawn = new DebugSubSpawn();
    }

    @Override
    protected void execute(Player sender, String[] args) {

        if(args.length < 2){
            sender.sendMessage(Utils.colorize("invalid syntax: /debug spawn HaloAura"));
            return;
        }

        switch (args[0].toLowerCase()){
            case "spawn": {
                this.debugsuspawn.dispatch(sender, args);
                break;
            }
        }

    }
}
