package xyz.sk1.bukkit.prisonextra.utils.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPCObserver;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.PrisonNPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.internal.cache.Cache;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

import java.util.Iterator;
import java.util.Map;

public class NPCSync extends BukkitRunnable {

    private UserManager userManager;
    private FakePlayerManager fakePlayerManager;

    public NPCSync(){
        this.userManager = (UserManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        this.fakePlayerManager = (FakePlayerManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.NPC);
    }

    @Override
    public void run() {

        Utils.LOG.info("outside the loop");
        Utils.LOG.info("entryset: " + this.fakePlayerManager.getCACHE().entrySet());
        for (Map.Entry entry : this.fakePlayerManager.getCACHE().entrySet()){
            Utils.LOG.info("test" + entry.getValue());
            this.fakePlayerManager.syncNPC((PrisonNPC) entry.getValue(), this.fakePlayerManager.getNpcObservers());
        }

    }

}
