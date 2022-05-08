package xyz.bobkinn_.serverjoinblocker;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyReloadEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class ServerJoinBlocker extends Plugin implements Listener {
    public static File configFile;
    public static Configuration configuration;
    public File dataFolder = getDataFolder();


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting...");
        String[] bEnableAliases= {"blockjoinenable","bon"};
        String[] bDisableAliases={"blockjoindisable","boff"};
        configLoad();

        getProxy().getPluginManager().registerCommand(this, new CommandEnable("benable","serverjoinblocker.enable", bEnableAliases));
        getProxy().getPluginManager().registerCommand(this, new CommandDisable("bdisable","serverjoinblocker.disable", bDisableAliases));
        getProxy().getPluginManager().registerListener(this,this);
        getLogger().info("Started!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled!");
    }


    @EventHandler
    public void onConnect(PreLoginEvent e) {
        boolean blockEnabled = configuration.getBoolean("blockEnabled");
        String kickMsg = configuration.getString("kickMsg").replace("&","§");
        List<String> allowJoinTo = configuration.getStringList("allowJoinTo");
        String pName = e.getConnection().getName();
        if (blockEnabled && !allowJoinTo.contains(pName)){
            e.getConnection().disconnect(new ComponentBuilder(kickMsg).create());
            e.setCancelled(true);
            e.setCancelReason(new ComponentBuilder(kickMsg).create());
        }

    }

    @EventHandler
    public void onReload(ProxyReloadEvent e){
        String reloadMsg = configuration.getString("reloadMsg").replace("&","§");
        configLoad();
        getLogger().info(reloadMsg);
    }

    public void configLoad() {
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        configFile = new File(getDataFolder(), "config.yml");

        try {
            if(!configFile.exists()) {
                configFile.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!configuration.contains("enableMsg")){
            configuration.set("enableMsg", "&cJoins blocked!");

            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!configuration.contains("enableMsgAlready")){
            configuration.set("enableMsgAlready", "Joins already &cblocked");

            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!configuration.contains("disableMsg")){
            configuration.set("disableMsg", "&aJoins allowed!");
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (!configuration.contains("disableMsgAlready")){
            configuration.set("disableMsgAlready", "Joins already &aallowed");
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (!configuration.contains("kickMsg")){
            configuration.set("kickMsg", "&cSorry, you can`t join now");
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (!configuration.contains("reloadMsg")){
            configuration.set("reloadMsg", "&aConfiguration reloaded!");
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (!configuration.contains("allowJoinTo")){
            String[] emptyList={};
            configuration.set("allowJoinTo", emptyList);
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if (!configuration.contains("blockEnabled")){
            configuration.set("blockEnabled", false);
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
