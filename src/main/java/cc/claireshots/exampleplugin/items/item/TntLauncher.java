package cc.claireshots.exampleplugin.items.item;

import cc.claireshots.exampleplugin.events.listener.TNTCannonListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class TntLauncher extends CustomItem {

    public TntLauncher() {
        super("tnt_launcher", "TNT Cannon", Material.DIAMOND_HORSE_ARMOR, 1);
    }

    @Override
    public List<String> Lore() {
        List<String> lore = new ArrayList<>();
        lore.add("&eRight Click &7to launch TNT!!");
        return lore;
    }

    @Override
    public Listener itemListener() {
        return new TNTCannonListener();
    }
}
