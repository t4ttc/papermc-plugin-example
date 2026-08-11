package cc.claireshots.exampleplugin.items.helper;

import cc.claireshots.exampleplugin.items.item.CustomItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemRegister {
    private static final Map<String, CustomItem> items = new HashMap<>();
    public static void registerItem(CustomItem item) {
        items.put(item.Id(), item);
        item.registerListener();
    }
    public static CustomItem getItem(String ID) {
        return items.get(ID);
    }
    public static Collection<String> getItemIDs() {
        return items.keySet();
    }
}
