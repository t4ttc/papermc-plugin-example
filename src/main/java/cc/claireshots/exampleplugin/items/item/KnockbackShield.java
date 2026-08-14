package cc.claireshots.exampleplugin.items.item;

import cc.claireshots.exampleplugin.events.listener.KnockbackShieldListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;

public class KnockbackShield extends CustomItem {

    public KnockbackShield() {
        super("knockback_shield", "&eKnockback Shield", Material.SHIELD, 2);
    }

    @Override
    public boolean isEnchanted() {
        return true;
    }

    @Override
    public Listener itemListener() {
        return new KnockbackShieldListener();
    }
}
