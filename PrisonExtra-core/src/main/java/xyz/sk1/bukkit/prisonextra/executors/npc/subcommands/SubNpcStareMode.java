package xyz.sk1.bukkit.prisonextra.executors.npc.subcommands;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class SubNpcStareMode implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        if(args.length < 2)
            return;

        FileConfiguration configuration = ((YamlConfigurationHandler)Core.getInstance().getSettings()).get();
        boolean should = configuration.getBoolean("entities.npcs.global.stare");

        sender.playSound(sender.getLocation(), Sound.LEVEL_UP, 20f, 20f);

        sender.sendMessage(Utils.colorize("&fStare mode is &b"+!should+" &ffor NPCs"));
        configuration.set("entities.npcs.global.stare", !should);

    }
}
