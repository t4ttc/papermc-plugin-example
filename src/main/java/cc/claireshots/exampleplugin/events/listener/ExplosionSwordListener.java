package cc.claireshots.exampleplugin.events.listener;

import cc.claireshots.exampleplugin.Exampleplugin;
import cc.claireshots.exampleplugin.items.helper.ItemHelper;
import cc.claireshots.exampleplugin.items.item.ExplosionSword;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class ExplosionSwordListener implements Listener {
    @EventHandler
    private void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!ItemHelper.isHoldingCustomItem((Player) event.getDamager(), new ExplosionSword().ID())) return;
        Entity victim = event.getEntity();
        if (victim instanceof Player) {
            if (((Player) victim).isBlocking()) return; // Don't continue if player is blocking with a shield
        }
        Vector vector = victim.getVelocity();
        Bukkit.getScheduler().runTaskLater(Exampleplugin.getInstance(), () -> {
            vector.setX(0);
            vector.setZ(0);
            vector.setY(1.75);
            victim.setVelocity(vector);
        }, 1L);
        Bukkit.getScheduler().runTaskLater(Exampleplugin.getInstance(), () -> {
            victim.getWorld().createExplosion(victim.getLocation(), 4.0F, false, false);
        }, 20L);
    }
}
