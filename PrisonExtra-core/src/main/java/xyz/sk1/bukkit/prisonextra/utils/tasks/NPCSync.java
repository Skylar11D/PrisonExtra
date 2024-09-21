package xyz.sk1.bukkit.prisonextra.utils.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
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

        for (Map.Entry<Integer, NPC> entry : this.fakePlayerManager.getCache().entrySet()){
            this.fakePlayerManager.syncNPC(entry.getValue(), this.fakePlayerManager.getNpcObservers());
        }

    }

}