package xyz.sk1.bukkit.prisonextra.internal.storage;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.components.InteractionModifier;
import dev.triumphteam.gui.guis.Gui;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class PDatabase implements Closeable {

    public abstract void connect();
    public abstract Connection getConnection();

}