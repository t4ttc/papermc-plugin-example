package cc.claireshots.exampleplugin.commands.command;

import cc.claireshots.exampleplugin.items.helper.ItemRegister;
import cc.claireshots.exampleplugin.items.item.CustomItem;
import cc.claireshots.exampleplugin.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveItemCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length < 2) {
            Msg.send(sender, "&cYou must specify the player to give the item to and the item ID.");
            return true;
        }
        if (!sender.isOp()) {
            Msg.send(sender, "&cYou must be operator to perform this command");
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            Msg.send(sender, "&cCouldn't find player " + args[0]);
            return true;
        }

        CustomItem item = ItemRegister.getItem(args[1]);

        if (item == null) {
            Msg.send(sender, "&cCouldn't find item ID " + args[1]);
            return true;
        }

        item.give(target);
        Msg.send(target, sender.getName() + " gave you " + item.Name());
        Msg.send(sender, "&eGave 1 " + item.Name() + " &eto " + target.getName());

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> options = new ArrayList<>();
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                options.add(player.getName());
            }
            return options;
        }
        if (args.length == 2) {
            options.addAll(ItemRegister.getItemIDs());
            return options;
        }
        return options;
    }
}
