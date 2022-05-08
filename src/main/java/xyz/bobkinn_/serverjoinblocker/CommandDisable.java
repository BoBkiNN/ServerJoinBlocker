package xyz.bobkinn_.serverjoinblocker;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CommandDisable extends Command{

    public CommandDisable(String name, String permission, String[] aliases, File dataFolder) {
        super(name,permission,aliases);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        boolean blockEnabled = ServerJoinBlocker.configuration.getBoolean("blockEnabled");
        String enableMsgAlready = ServerJoinBlocker.configuration.getString("disableMsgAlready").replace("&","§");
        String enableMsg = ServerJoinBlocker.configuration.getString("disableMsg").replace("&","§");
        if (!blockEnabled){
            sender.sendMessage( new ComponentBuilder (enableMsgAlready).create());
        } else {
            ServerJoinBlocker.configuration.set("blockEnabled", false);
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(ServerJoinBlocker.configuration, ServerJoinBlocker.configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage(new ComponentBuilder (enableMsg).create());
        }


    }
}
