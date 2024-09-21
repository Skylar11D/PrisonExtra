package xyz.sk1.bukkit.prisonextra.listeners;

import org.bukkit.event.Event;

public interface PrisonEventHandler<T extends Event> {

    void handle(T event);

}