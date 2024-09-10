package xyz.sk1.bukkit.prisonextra.prisoner;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.enums.Cosmetics;

public interface Prisoner {

    Player getPlayer();

    default void spawnMiner(){}

    default void summonParticle(Cosmetics.ParticleType type){}

    default void rentHouse(){}

    default void rentMiner(){}

    default void purchaseMiner(){}

    default void purchaseCosmetic(Cosmetics type){}

    default void summonCloak(Cosmetics.CloakType type){}

}
