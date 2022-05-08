package xyz.bobkinn_.serverjoinblocker;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class ServerJoinBlocker extends Plugin{
    public static File configFile;
    public static Configuration configuration;
    public File dataFolder = getDataFolder();
    public static File switchFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[SJB] Starting...");
        String[] bEnableAliases= {"blockjoinenable","bon"};
        String[] bDisableAliases={"blockjoindisable","boff"};
        configLoad();

        getProxy().getPluginManager().registerCommand(this, new CommandEnable("benable","serverjoinblocker.enable", bEnableAliases, dataFolder));
        getProxy().getPluginManager().registerCommand(this, new CommandDisable("bdisable","serverjoinblocker.disable", bDisableAliases, dataFolder));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    private void configLoad() {
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
            configuration.set("kickMsg", "Sorry, you cant join now");
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
