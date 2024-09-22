package xyz.sk1.bukkit.prisonextra.utils.builder;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemStackBuilder {

    private ItemStack itemStack;

    public ItemStackBuilder(Material material){

        itemStack = new ItemStack(material);

    }

    public ItemStackBuilder(Material material, int quantity){

        itemStack = new ItemStack(material, quantity);

    }

    public ItemStackBuilder(Material material, int quantity, byte data){

        itemStack = new ItemStack(material, quantity, (short) Integer.MAX_VALUE, data);

    }

    public ItemStackBuilder setName(String displayName){

        ItemMeta meta = this.itemStack.getItemMeta();

        meta.setDisplayName(displayName);

        this.itemStack.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder setEnchanted(boolean shouldEnchant){

        ItemMeta meta = this.itemStack.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.addEnchant(Enchantment.DAMAGE_ALL, 4, shouldEnchant);

        this.itemStack.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder setLore(String... lore){

        ItemMeta meta = this.itemStack.getItemMeta();

        meta.setLore(Arrays.asList(lore));

        this.itemStack.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder setUnbreakable(boolean unbreakable){

        ItemMeta meta = this.itemStack.getItemMeta();

        meta.spigot().setUnbreakable(unbreakable);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        this.itemStack.setItemMeta(meta);

        return this;
    }

    public ItemStackBuilder setMetaData(String key, String value){

        net.minecraft.server.v1_8_R3.ItemStack craftItemStack = CraftItemStack.asNMSCopy(this.itemStack);

        NBTTagCompound nbtTag = (craftItemStack.hasTag()) ? craftItemStack.getTag() : new NBTTagCompound();

        nbtTag.set(key, new NBTTagString(value));

        craftItemStack.setTag(nbtTag);

        return this;
    }

    public ItemStack build() {
        return this.itemStack;
    }
}
