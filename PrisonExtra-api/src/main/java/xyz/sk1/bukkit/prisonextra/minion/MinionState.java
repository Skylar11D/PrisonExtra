package xyz.sk1.bukkit.prisonextra.minion;

import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;

public interface MinionState {

    void perform(Prisoner prisoner, Minion miner);

}
