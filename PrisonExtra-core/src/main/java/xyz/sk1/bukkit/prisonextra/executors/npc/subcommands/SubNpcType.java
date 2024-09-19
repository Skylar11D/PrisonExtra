package xyz.sk1.bukkit.prisonextra.executors.npc.subcommands;


import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.internal.configuration.YamlConfigurationHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class SubNpcType implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        YamlConfigurationHandler yamlSettings = (YamlConfigurationHandler) Core.getInstance().getSettings();

        if(!args[2].equalsIgnoreCase("type")) {
            sender.sendMessage(Utils.colorize("&cInvalid third argument!"));
            return;
        }
        if(args[3].isEmpty()){
            sender.sendMessage(Utils.colorize("&cInvalid type!"));
            return;
        }

        if(!args[3].equalsIgnoreCase("general")){
            sender.sendMessage(Utils.colorize("&cThe specified type doesn't exist!"));
            return;
        }

        String texture = yamlSettings.get().getString("npcs."+args[1]+".appearance.texture");
        String signature = yamlSettings.get().getString("npcs."+args[1]+".appearance.signature");

        yamlSettings.get().set("npcs."+args[1]+".coordinates.x", sender.getLocation().getX());
        yamlSettings.get().set("npcs."+args[1]+".coordinates.y", sender.getLocation().getY());
        yamlSettings.get().set("npcs."+args[1]+".coordinates.z", sender.getLocation().getZ());
        yamlSettings.get().set("npcs."+args[1]+".coordinates.world", sender.getLocation().getWorld().getName());

       yamlSettings.get().set("npcs."+args[1]+".attributes.type", args[3]);

        ((FakePlayerManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.NPC)).getFactory().createTextured(args[1], texture, signature, sender.getLocation());

    }
}
