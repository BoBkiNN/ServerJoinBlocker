package xyz.bobkinn_.serverjoinblocker;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;

public class CommandEnable extends Command{

    public CommandEnable(String name, String permission, String[] aliases) {
        super(name,permission,aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        boolean blockEnabled = ServerJoinBlocker.configuration.getBoolean("blockEnabled");
        String enableMsgAlready = ChatColor.translateAlternateColorCodes('&',ServerJoinBlocker.configuration.getString("enableMsgAlready"));
        String enableMsg = ChatColor.translateAlternateColorCodes('&',ServerJoinBlocker.configuration.getString("enableMsg"));
        if (blockEnabled){
            sender.sendMessage( new ComponentBuilder (enableMsgAlready).create());
        } else {
            ServerJoinBlocker.configuration.set("blockEnabled", true);
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(ServerJoinBlocker.configuration, ServerJoinBlocker.configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage(new ComponentBuilder (enableMsg).create());
        }


    }
}
