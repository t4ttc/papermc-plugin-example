package cc.claireshots.exampleplugin.events.listener;

import cc.claireshots.exampleplugin.items.helper.ItemHelper;
import cc.claireshots.exampleplugin.items.item.TntLauncher;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class TNTCannonListener implements Listener {
    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        if (!ItemHelper.isHoldingCustomItem(event.getPlayer(), new TntLauncher().ID())) return;
        Location loc = event.getPlayer().getLocation();
        TNTPrimed tnt = (TNTPrimed) loc.getWorld().spawnEntity(loc, EntityType.TNT);
        tnt.setFuseTicks(40);
        Vector forward = event.getPlayer().getLocation().getDirection();
        forward.multiply(1.5);
        forward.setY(0.25);
        tnt.setVelocity(forward);
    }
}
