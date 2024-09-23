package xyz.sk1.bukkit.prisonextra.executors.house;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.executors.Attributes;
import xyz.sk1.bukkit.prisonextra.executors.Executor;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.executors.house.subcommands.HouseWand;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

@Attributes(name = "house", requiresPlayer = true, permission = "marsmc.house")
public class HouseCommand extends Executor {

    private final Subcommand houseWand, houseCreate;

    public HouseCommand() {
        this.houseCreate = new HouseWand();
        this.houseWand = new HouseWand();
    }

    protected void execute(Player sender, String[] args) {
        if(args.length < 1){
            sender.sendMessage(Utils.colorize("&c/region wand"));
            sender.sendMessage(Utils.colorize("&c/region create (&7mark the corners first&c)"));
        }

        switch (args[0].toLowerCase()){
            case "wand": {
                houseWand.dispatch(sender, args);
            }

            case "create": {
                houseCreate.dispatch(sender, args);
            }
        }

    }
}
