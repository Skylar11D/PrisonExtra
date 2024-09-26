package xyz.sk1.bukkit.prisonextra.executors.npc.subcommands;

import com.sun.deploy.ui.UITextArea;
import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.executors.Subcommand;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class SubNpcRemove implements Subcommand {

    @Override
    public void dispatch(Player sender, String[] args) {

        if(args[1].isEmpty()){
            sender.sendMessage("invalid name");
            return;
        }

        FakePlayerManager fakePlayerManager = (FakePlayerManager) Core.getInstance().
                getManagerRegistry().getManager(ManagerType.NPC);

        for (NPC npc : fakePlayerManager.getCache().values()){
            if(!npc.getHandle().getCustomName().equals(args[1]))
                continue;

            /*
            * TODO remove the entity from observers and config
            * */

            /*
             * TODO remove the entity from the local cache
             * */
            fakePlayerManager.getCache().remove(npc.getId(), npc);
            sender.sendMessage(Utils.colorize("&fEntity with id #"+npc.getId()));

        }

    }
}
