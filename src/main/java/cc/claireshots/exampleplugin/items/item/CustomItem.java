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

public interface CustomItem {
    // These MUST be defined by the user!
    String Id();
    Material Material();
    String Name();

    // Optional parameters
    default List<String> Lore() {
        return new ArrayList<>();
    }

    default Listener itemListener() {
        return new EmptyListener();
    }

    // Methods to get items
    default ItemStack buildItem() {
        ItemStack itemStack = new ItemStack(Material());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(Msg.component(Name())); // Set item name using componentizer
        itemMeta.lore(convertLoreToComponents()); // Set item lore using componentizer
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(ItemHelper.getCustomItemKey(), PersistentDataType.STRING, Id()); // Attach item ID to item

        itemStack.setItemMeta(itemMeta);

        registerListener(); // Register item listeners

        return itemStack;
    }
    default void give(Player player) {
        player.getInventory().addItem(buildItem());
    }

    default boolean listenerEnabled() {
        return ListenerRegisterHelper.isRegistered(itemListener());
    }

    default void registerListener() {
        if (!listenerEnabled()) {
            ListenerRegisterHelper.register(itemListener());
        }
    }

    default List<Component> convertLoreToComponents() {
        List<Component> componentizedLore = new ArrayList<>();
        for (String line : Lore()) {
            componentizedLore.add(Msg.component(line));
        }
        return componentizedLore;
    }
}
