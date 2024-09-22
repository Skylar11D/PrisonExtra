package xyz.sk1.bukkit.prisonextra.prisoner;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;

public interface Prisoner {

    Player getPlayer();

    default Minion getMiner(Minion miner){return null;}

    default void openMainMenu(){}

}