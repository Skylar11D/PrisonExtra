package xyz.sk1.bukkit.prisonextra.prisoner;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.inventory.MenuType;
import xyz.sk1.bukkit.prisonextra.perks.Perk;

public interface Prisoner {

    Player getHandle();

    default Minion getMiner(Minion miner){return null;}

    default void openMenu(MenuType type){}

    void summonPerk(Perk perk);

    void removePerk();

}