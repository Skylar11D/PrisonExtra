package xyz.sk1.bukkit.prisonextra.events.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.events.handler.interact.NpcInteractHandler;
import xyz.sk1.bukkit.prisonextra.listeners.BaseListener;
import xyz.sk1.bukkit.prisonextra.listeners.PrisonEventHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.player.User;
import xyz.sk1.bukkit.prisonextra.player.UserManager;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class PlayerInteractListener extends PacketAdapter {

    private final PrisonEventHandler<PlayerInteractAtEntityEvent> npcHandler;

    public PlayerInteractListener(){
        super(Core.getInstance(), PacketType.Play.Client.USE_ENTITY);
        Utils.LOG.info("registered a packet listener");
        this.npcHandler = new NpcInteractHandler();
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        FakePlayerManager fakePlayerManager = (FakePlayerManager) Core.getInstance().getManagerRegistry().getManager(ManagerType.NPC);
        UserManager userManager = (UserManager)Core.getInstance().getManagerRegistry().getManager(ManagerType.PRISON);
        User user = userManager.get(event.getPlayer());

        PacketContainer packet = event.getPacket();
        int targetId = event.getPacket().getIntegers().read(0);
        Utils.LOG.info("ignited");
        if(!fakePlayerManager.getCache().containsKey(targetId)) {
            Utils.LOG.info("avoided");
            return;
        }

        EnumWrappers.Hand hand = packet.getHands().read(0);
        EnumWrappers.EntityUseAction use = packet.getEnumEntityUseActions().read(0).getAction();

        if(hand == EnumWrappers.Hand.MAIN_HAND &&
                (use == EnumWrappers.EntityUseAction.INTERACT_AT || use == EnumWrappers.EntityUseAction.INTERACT)){

            Utils.LOG.info("succeeded");



        }

    }
}
