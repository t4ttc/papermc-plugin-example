package cc.claireshots.exampleplugin.events.helper;

import cc.claireshots.exampleplugin.Exampleplugin;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.Collection;
import java.util.HashSet;

public class ListenerRegisterHelper {
    private static Collection<Listener> registeredListeners = new HashSet<>();
    public static void register(Listener listener) {
        Exampleplugin instance = Exampleplugin.getInstance();
        instance.getServer().getPluginManager().registerEvents(listener, instance);
        registeredListeners.add(listener);
    }
    public static void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
        registeredListeners.remove(listener);
    }
    public static boolean isRegistered(Listener listener) {
        return registeredListeners.contains(listener);
    }
    public static boolean areAllRegistered(Collection<Listener> listeners) {
        return registeredListeners.containsAll(listeners);
    }
}
