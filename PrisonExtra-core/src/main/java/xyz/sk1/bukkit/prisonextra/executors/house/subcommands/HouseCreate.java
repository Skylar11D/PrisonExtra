package xyz.sk1.bukkit.prisonextra.executors.house.subcommands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.housing.House;
import xyz.sk1.bukkit.prisonextra.housing.HouseManager;
import xyz.sk1.bukkit.prisonextra.housing.factory.RegionFactory;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class HouseCreate implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {
        UserManager userManager = (UserManager) Core.getInstance().getManagerRegistry().getType(ManagerType.PRISON);
        HouseManager houseManager = (HouseManager) Core.getInstance().getManagerRegistry().getType(ManagerType.REGION);
        User user = userManager.get(sender);
        Location corner1 = user.getCorner1();
        Location corner2 = user.getCorner2();

        if(corner1 == null || corner2 == null) {
            sender.sendMessage(Utils.colorize("&cCorners must be marked first with the wand!"));
            sender.playSound(sender.getLocation(), Sound.NOTE_BASS, 4f, 4f);
            return;
        }

        House house = houseManager.createHouse(corner1, corner2, user.getHandle().getName());
        houseManager.insertRegion(house);

        user.setCorner1(null);
        user.setCorner2(null);

        sender.playSound(sender.getLocation(), Sound.LEVEL_UP, 15f, 15f);
        sender.sendMessage(Utils.colorize("&aA new house has been created!"));

    }
}
