package xyz.sk1.bukkit.prisonextra.events.listeners.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.events.handler.interact.NpcInteractHandler;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.utilities.Utils;

public class PlayerInteractPacketListener extends PacketAdapter {

    private final NpcInteractHandler npcHandler;

    public PlayerInteractPacketListener(){
        super(Core.getInstance(), PacketType.Play.Client.USE_ENTITY);
        Utils.LOG.info("registered a packet listener");
        this.npcHandler = new NpcInteractHandler();
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        FakePlayerManager fakePlayerManager = (FakePlayerManager) Core.getInstance().
                getManagerRegistry().getManager(ManagerType.NPC);


        int targetId = event.getPacket().getIntegers().read(0);

        if(!(fakePlayerManager.getCache().containsKey(targetId)))
            return;

        EnumWrappers.EntityUseAction action = event.getPacket().getEntityUseActions().read(0);
        EnumWrappers.Hand hand = event.getPacket().getHands().read(0);

        if(hand != EnumWrappers.Hand.MAIN_HAND && action != EnumWrappers.EntityUseAction.INTERACT)
            return;

        npcHandler.handle(event);

    }
}
