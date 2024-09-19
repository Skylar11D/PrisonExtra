package xyz.sk1.bukkit.prisonextra.executors.npc.subcommands;


import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class SubNpcType implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        YamlConfigurationHandler yamlSettings = (YamlConfigurationHandler) Core.getInstance().getSettings();

        if(!args[2].equalsIgnoreCase("type")) {
            sender.sendMessage(Utils.colorize("&cInvalid third argument!"));
            return;
        }

        if(!args[3].equalsIgnoreCase("general")){
            sender.sendMessage(Utils.colorize("&cThe specified type doesn't exist!"));
            return;
        }

        yamlSettings.get().set("entities.npcs."+args[1]+".coordinates.x", sender.getLocation().getX());
        yamlSettings.get().set("entities.npcs."+args[1]+".coordinates.y", sender.getLocation().getY());
        yamlSettings.get().set("entities.npcs."+args[1]+".coordinates.z", sender.getLocation().getZ());
        yamlSettings.get().set("entities.npcs."+args[1]+".coordinates.world", sender.getLocation().getWorld().getName());

       yamlSettings.get().set("entities.npcs."+args[1]+".attributes.type", args[3]);

        ((FakePlayerManager)Core.getInstance().
                getManagerRegistry().getManager(ManagerType.NPC)).
                getNpcFactory().createPlain(args[1], sender.getLocation());

        sender.playSound(sender.getLocation(), Sound.LEVEL_UP, 0.9f, 0.9f);

        sender.sendMessage(Utils.colorize("&fSuccessfully created &b"+args[1].toUpperCase()+" &fas a NPC!"));

        UserManager manager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        manager.get(sender).getPlayer().sendMessage(Utils.colorize("&c[DEBUG] &aYou are a prisoner!"));
        Utils.LOG.info("player is: " + manager.get(sender).getPlayer().getDisplayName());
    }
}
