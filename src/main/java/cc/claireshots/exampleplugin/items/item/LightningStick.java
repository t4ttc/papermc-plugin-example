package cc.claireshots.exampleplugin.items.item;

import cc.claireshots.exampleplugin.events.listener.LightningStickListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * This is an example usage of a custom item. It defines the three required fields (Id, Material, Name)
 * and also specifies an optional parameter (itemListener) - which uses a PlayerInteractEvent to strike lightning
 * at the targeted block.
 */
public class LightningStick implements CustomItem {
    @Override
    public String Id() {
        return "lightning_stick";
    }

    @Override
    public Material Material() {
        return Material.STICK;
    }

    @Override
    public String Name() {
        return "&eLightning Stick";
    }

    @Override
    public Listener itemListener() {
        return new LightningStickListener();
    }
}
