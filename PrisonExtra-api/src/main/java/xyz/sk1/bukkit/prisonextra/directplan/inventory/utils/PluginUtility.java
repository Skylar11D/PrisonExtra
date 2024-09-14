package xyz.sk1.bukkit.prisonextra.directplan.inventory.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.title.Title;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorldBorder;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import java.awt.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PluginUtility {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final DecimalFormat decimalFormat = new DecimalFormat("##.#");
    private static final Pattern COLOR_PATTERN = Pattern.compile("(?i)" + '&' + "[0-9A-FK-OR]");


    public static boolean hasChanceOccurred(int percentChance) {
        return getRandomInteger(1, 101) <= percentChance;
    }

    // int
    public static int getRandomInteger(int minimum, int maximum) {
        int randomPercentage = random.nextInt(maximum);
        if(randomPercentage < minimum) {
            randomPercentage = minimum;
        }
        return randomPercentage;
    }

    public static <T> T getRandom(List<T> collection) {
        int randomIndex = random.nextInt(collection.size());
        return collection.get(randomIndex);
    }

    public static <T> T getRandomElement(T... elements) {
        int randomIndex = random.nextInt(elements.length);
        return elements[randomIndex];
    }

    public static String translateMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(translateMessage(message));
    }

    public static boolean isEmpty(Inventory inventory) {
        ItemStack[] contents = inventory.getContents();
        for(ItemStack content : contents) {
            if(content != null) {
                return false;
            }
        }
        return true;
    }
    public static boolean isInventoryEmpty(Player player) {
        Inventory inventory = player.getInventory();
        return isEmpty(inventory);
    }

    public static boolean doesLocationMatch(Location first, Location second) {
        int xf = (int) first.getX();
        int yf = (int) first.getY();
        int zf = (int) first.getZ();

        int xs = (int) second.getX();
        int ys = (int) second.getY();
        int zs = (int) second.getZ();

        return (xf == xs && yf == ys && zf == zs);
    }
    private static final FireworkEffect.Type[] effects = FireworkEffect.Type.values();

    public static void spawnCoolFirework(Player player) {
        spawnCoolFirework(player.getLocation());
    }
    public static void spawnCoolFirework(Location location) {

        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkEffect.Type randomEffect = effects[random.nextInt(effects.length)];

        FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(false)
                .withColor(Color.RED, Color.GREEN, Color.BLUE, Color.PURPLE).trail(false).with(randomEffect)
                .build();

        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        try {
            Field field = fireworkMeta.getClass().getDeclaredField("power");
            field.setAccessible(true);
            field.set(fireworkMeta, 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        fireworkMeta.addEffect(fireworkEffect);

        firework.setFireworkMeta(fireworkMeta);
    }

    public static void playSound(Player player, Sound sound) {
        playSound(player, player.getLocation(), sound);
    }

    public static void playSound(Player player, Location location, Sound sound) {
        player.playSound(location, sound, 1f, 1f);
    }

    public static void spawnParticle(Location location, EnumParticle particle) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float) location.getX(),
                (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, 15);
        for (Player online : Bukkit.getOnlinePlayers()) {
            EntityPlayer entityPlayer = ((CraftPlayer)online).getHandle();
            PlayerConnection playerConnection = entityPlayer.playerConnection;
            playerConnection.sendPacket(packet);
        }
    }

    public static void playChestAction(Chest chest, boolean open) {
        Location location = chest.getLocation();
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
        TileEntityChest tileChest = (TileEntityChest) world.getTileEntity(position);
        world.playBlockAction(position, tileChest.w(), 1, open ? 1 : 0);
    }

    public static int getRowsBasedOnSlots(int slots) {
        double divided = 9.0;
        double rows = (slots / divided);
        if(slots % divided != 0.0) return (int) rows + 1;
        return (int) rows;
    }

    public static int getPercentage(int current, int max) {
        double percentage = ((current * 1.0) / max) * 100.0;
        return (int) percentage;
    }

    public static String formatDoubleDecimal(double d) {
        return decimalFormat.format(d);
    }

    public static String getItemNativeName(ItemStack item) {
        if(item.getType() == Material.AIR) {
            return ""; // Air
        }
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        return nmsStack.getItem().a(nmsStack);
    }

    public static int getItemArmorSlot(ItemStack itemStack) {
        String[] arr = itemStack.getType().name().split("_");
        if(arr.length < 2) return -1;
        switch (arr[1]) {
            case "HELMET":
                return 39;
            case "CHESTPLATE":
                return 38;
            case "LEGGINGS":
                return 37;
            case "BOOTS":
                return 36;
            default:
                return -1;
        }
    }

    public static void removeStuckArrowsFromPlayer(Player player) {
        ((CraftPlayer)player).getHandle().getDataWatcher().watch(9, (byte) 0);
    }

    public static void connectToServer(JavaPlugin plugin, Player player, String server) {

        String platform = "BungeeCord";
        Messenger messenger = plugin.getServer().getMessenger();
        if(!messenger.isOutgoingChannelRegistered(plugin, platform)) {
            messenger.registerOutgoingPluginChannel(plugin, platform);
        }
        ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
        byteArrayDataOutput.writeUTF("Connect");
        byteArrayDataOutput.writeUTF(server);

        player.sendPluginMessage(plugin, platform, byteArrayDataOutput.toByteArray());
    }

    public static String getColors(String input) {
        StringBuilder builder = new StringBuilder();
        Matcher matcher = COLOR_PATTERN.matcher(input);
        while (matcher.find()) {
            builder.append(matcher.group());
        }
        return builder.toString();
    }
}
