package cc.claireshots.exampleplugin.events.listener;

import cc.claireshots.exampleplugin.items.helper.ItemHelper;
import cc.claireshots.exampleplugin.items.item.LightningStick;
import org.bukkit.Location;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LightningStickListener implements Listener {
    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        if (ItemHelper.isHoldingCustomItem(event.getPlayer(), new LightningStick().ID())) {
            Location strikeLocation = event.getPlayer().getTargetBlock(null, 10)
                    .getLocation();
            event.getPlayer().getWorld().strikeLightning(strikeLocation);
        }
    }
}
