package cc.claireshots.exampleplugin.items.helper;

import cc.claireshots.exampleplugin.Exampleplugin;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemHelper {
    private static NamespacedKey CustomItemKey = new NamespacedKey(Exampleplugin.getInstance(), "customitems");

    public static NamespacedKey getCustomItemKey() {
        return CustomItemKey;
    }

    public static boolean isHoldingCustomItem(Player player, String ID) {
        return isHoldingCustomItem(player, ID, false);
    }
    public static boolean isHoldingCustomItem(Player player, String ID, boolean offhand) {
        if (!offhand) {
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (!container.has(CustomItemKey)) return false;
            if (container.get(CustomItemKey, PersistentDataType.STRING).equalsIgnoreCase(ID)) return true;
        }
        if (offhand) {
            ItemStack item = player.getInventory().getItemInOffHand();
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (!container.has(CustomItemKey)) return false;
            if (container.get(CustomItemKey, PersistentDataType.STRING).equalsIgnoreCase(ID)) return true;
        }
        return false;
    }
}
