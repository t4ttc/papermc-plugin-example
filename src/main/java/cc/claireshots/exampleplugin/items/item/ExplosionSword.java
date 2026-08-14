package cc.claireshots.exampleplugin.items.item;

import cc.claireshots.exampleplugin.events.listener.ExplosionSwordListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ExplosionSword extends CustomItem {
    public ExplosionSword() {
        super("explosion_sword", "&2Explosive Sword", Material.EMERALD, 2);
    }

    @Override
    public Listener itemListener() {
        return new ExplosionSwordListener();
    }
}
