package cc.claireshots.exampleplugin;

import cc.claireshots.exampleplugin.commands.command.GiveItemCommand;
import cc.claireshots.exampleplugin.items.helper.ItemRegister;
import cc.claireshots.exampleplugin.items.helper.ItemUpdateHelper;
import cc.claireshots.exampleplugin.items.item.*;
import io.papermc.paper.registry.keys.DataComponentTypeKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.K;

public final class Exampleplugin extends JavaPlugin {
    private static Exampleplugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommands();
        registerItems();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Exampleplugin getInstance() {
        return instance;
    }

    private void registerCommands() {
        getCommand("givecustom").setExecutor(new GiveItemCommand());
    }

    private void registerItems() {
        ItemUpdateHelper.enableScheduledUpdater();
        ItemRegister.registerItem(new LightningStick());
        ItemRegister.registerItem(new TntLauncher());
        ItemRegister.registerItem(new ExplosionSword());
        ItemRegister.registerItem(new KnockbackShield());
    }
}
