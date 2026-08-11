package cc.claireshots.exampleplugin.items.helper;

import cc.claireshots.exampleplugin.Exampleplugin;
import cc.claireshots.exampleplugin.items.item.CustomItem;
import cc.claireshots.exampleplugin.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemUpdateHelper {
    public static void enableScheduledUpdater() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updatePlayerCustomItems(player);
                }
            }
        };
        task.runTaskTimerAsynchronously(Exampleplugin.getInstance(), 0L, 100L);
    }

    private static void updatePlayerCustomItems(Player player) {
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item == null) continue;
            if (!item.isEmpty()) {
                //Msg.send(player, "Detected non-empty item");
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(ItemHelper.getCustomItemKey())) {
                    //Msg.send(player, "Detected customitem");
                    CustomItem cItem = ItemRegister.getItem(container.get(ItemHelper.getCustomItemKey(), PersistentDataType.STRING));
                    if (cItem == null) {
                        //Msg.send(player, "Detected Null custom");
                        player.getInventory().setItem(i, new ItemStack(Material.AIR));
                    }
                    if (!container.has(ItemHelper.getItemVersionKey())) continue;
                    //Msg.send(player, "Made it past version key check");
                    if (container.get(ItemHelper.getItemVersionKey(), PersistentDataType.INTEGER) != cItem.Version()) {
                        //Msg.send(player, "Updating an item");
                        player.getInventory().setItem(i, cItem.buildItem());
                    }
                }
            }
        }
    }
}
