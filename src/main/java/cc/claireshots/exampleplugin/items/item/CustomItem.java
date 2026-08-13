package cc.claireshots.exampleplugin.items.item;

import cc.claireshots.exampleplugin.Exampleplugin;
import cc.claireshots.exampleplugin.events.helper.ListenerRegisterHelper;
import cc.claireshots.exampleplugin.events.listener.EmptyListener;
import cc.claireshots.exampleplugin.items.helper.ItemHelper;
import cc.claireshots.exampleplugin.util.Msg;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.RegisteredListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class CustomItem {
    private final String itemID;
    private final String itemName;
    private final int version;
    private final Material material;


    public CustomItem(String itemID, String itemName, Material material, int version) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.version = version;
        this.material = material;
    }

    // Optional parameters
    public List<String> Lore() {
        return new ArrayList<>();
    }

    public Listener itemListener() {
        return new EmptyListener();
    }

    // Methods to get items
    public final ItemStack buildItem() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(Msg.component(this.itemName)); // Set item name using componentizer
        itemMeta.lore(convertLoreToComponents()); // Set item lore using componentizer
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(ItemHelper.getCustomItemKey(), PersistentDataType.STRING, this.itemID); // Attach item ID to item
        container.set(ItemHelper.getItemVersionKey(), PersistentDataType.INTEGER, this.version);
        itemMeta.setEnchantmentGlintOverride(isEnchanted());

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public void give(Player player) {
        player.getInventory().addItem(buildItem());
    }

    private boolean listenerEnabled() {
        return ListenerRegisterHelper.isRegistered(itemListener());
    }

    public final void registerListener() {
        if (!listenerEnabled()) {
            ListenerRegisterHelper.register(itemListener());
        }
    }

    private List<Component> convertLoreToComponents() {
        List<Component> componentizedLore = new ArrayList<>();
        for (String line : Lore()) {
            componentizedLore.add(Msg.component(line));
        }
        return componentizedLore;
    }

    public final String ID() {
        return itemID;
    }

    public final String Name() {
        return itemName;
    }

    public final int Version() {
        return version;
    }

    public final Material Material() {
        return material;
    }
    public boolean isEnchanted() { return false; }
}
