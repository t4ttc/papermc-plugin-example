package cc.claireshots.exampleplugin.events.listener;

import cc.claireshots.exampleplugin.items.helper.ItemHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class KnockbackShieldListener implements Listener {
    @EventHandler
    private void onShield(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!ItemHelper.isHoldingCustomItem(((Player) event.getEntity()), "knockback_shield") && !ItemHelper.isHoldingCustomItem(((Player) event.getEntity()), "knockback_shield", true)) return;
        if (!((Player) event.getEntity()).isBlocking()) return;

        Vector vel = event.getDamager().getLocation().getDirection();
        vel.multiply(-1.2);
        event.getDamager().setVelocity(vel);


    }
}
