package xyz.bobkinn_.serverjoinblocker;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

import java.io.File;

public class CommandReload extends Command {
    public CommandReload(String name, String permission, String[] aliases, File dataFolder) {
        super(name,permission,aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ServerJoinBlocker main = new ServerJoinBlocker();
        main.configLoad();
        String reloadMsg = ServerJoinBlocker.configuration.getString("reloadMsg");
        sender.sendMessage(new ComponentBuilder(reloadMsg).create());
    }
}
