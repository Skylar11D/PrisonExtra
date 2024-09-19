package xyz.sk1.bukkit.prisonextra.executors;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public interface Subcommand {

    default void dispatch(Player sender, String[] args){}
    default void dispatch(ConsoleCommandSender sender, String[] args){}

}
