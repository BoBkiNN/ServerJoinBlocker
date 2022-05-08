package xyz.bobkinn_.serverjoinblocker;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

import java.io.File;

public class CommandEnable extends Command{
    public CommandEnable(String name, String permission, String[] aliases, File dataFolder) {
        super(name,permission,aliases);
    }



    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage( new ComponentBuilder ("benable cmd "+sender.getName()).color(ChatColor.GREEN).create());
    }
}
