package xyz.sk1.bukkit.prisonextra.executors;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public abstract class Executor implements CommandExecutor {

    @Getter
    private Attributes attributes;

    public Executor(){
        this.attributes = this.getClass().getDeclaredAnnotation(Attributes.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!attributes.permission().isEmpty()){
            if(!sender.hasPermission(attributes.permission())){
                sender.sendMessage(Utils.COLORIZE("You can't access this command (permission required)"));
                return false;
            }
        }

        if(attributes.requiresPlayer()){
            if(!(sender instanceof Player)) {
                sender.sendMessage("You're not qualified to execute this command");
                return true;
            }
            execute((Player) sender, args);
        }

        execute((ConsoleCommandSender) sender, args);

        return true;
    }

    protected void execute(Player sender, String[] args){}
    protected void execute(ConsoleCommandSender sender, String[] args){}

}
