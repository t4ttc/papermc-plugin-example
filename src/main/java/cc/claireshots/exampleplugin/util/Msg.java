package cc.claireshots.exampleplugin.util;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Msg {
    // Pulled from t4ttc/horsestom
    public static Component component(String text) {
        return component(text, false);
    }

    public static Component component(String text, boolean allowItalics) {
        return LegacyComponentSerializer.legacy('&').deserialize(text).decoration(TextDecoration.ITALIC, allowItalics);
    }

    public static void send(Audience audience, String message, boolean useColour) {
        audience.sendMessage(component(message, false));
    }

    public static void send(Audience audience, String message, String prefix) {
        Msg.send(audience, prefix + message, true);
    }

    public static void send(Audience audience, String message) {
        send(audience, message, "&7");
    }
}
