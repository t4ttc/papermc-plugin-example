package cc.claireshots.exampleplugin.items.item;

import cc.claireshots.exampleplugin.events.listener.LightningStickListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an example usage of a custom item. It defines the four required fields (Id, Material, Name, Version)
 * and also specifies an optional parameter (itemListener) - which uses a PlayerInteractEvent to strike lightning
 * at the targeted block.
 */
public class LightningStick extends CustomItem {

    /**
     * This is where the item is instantiated.
     * Here, we set up some important information that the item is required to have.
     *
     * We define our custom item ID used for identification in the first argument.
     * Changing item IDs is NOT supported!!!!! Unexpected results may occur.
     *
     * We define the display name of our item in the second argument
     *       - You can also use colour codes! (i.e. &a for green and &6 for gold)
     *
     * We define the item Material in the third argument - this is what the item will look like in game.
     *
     * We define the version number in the last slot. This can be any number, but every time you change the item
     * you should change / increment it. Best use is just increment by 1 every time you make a change.
     * This allows us to compare if an item is out of date, and replace it with a new version.
     * The automatic replacement of outdated items is handled for you
     * @see cc.claireshots.exampleplugin.items.helper.ItemUpdateHelper for the item update system
     */
    public LightningStick() {
        super("lightning_stick", "&6Lightning Stick", Material.STICK, 3);
    }

    /**
     * This is an optional parameter that items can have that allow us to attach an Event Listener class
     * to an item. This allows us to listen for event(s) relating to the item.
     * To use this, simply create your EventListenerClass and then override this function to return
     * a new instance of the Listener class.
     * @return The listener instance that the item will be tied to.
     */
    @Override
    public Listener itemListener() {
        return new LightningStickListener();
    }

    /**
     * Here's another optional parameter that we can use to spice our items up.
     * Here, you can create a List of Strings that act as our item's tooltip!
     * Each line in the list is a separate line in the "lore" / tooltip.
     * @return The item's lore as a List of Strings
     */
    @Override
    public List<String> Lore() {
        List<String> lore = new ArrayList<>();
        lore.add("&7Example lightning stick item!");
        lore.add("&eRight Click &7to zap lightning!!");
        lore.add("&7View the code in events.listener.LightningStickListener!");
        return lore;
    }
}
