package xyz.sk1.bukkit.prisonextra.executors;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class Executor implements CommandExecutor {

    @Getter
    private ACommand aCommand;

    public Executor(){
        this.aCommand = this.getClass().getDeclaredAnnotation(ACommand.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        return false;
    }
}
