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

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[SJB] Starting...");
        String[] bEnableAliases= {"blockjoinenable"};
        configLoad();
        File switchFile = new File(dataFolder, "switch.txt");
        if (!switchFile.exists()){
            try {
                switchFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter writer = new FileWriter(switchFile, false);
                writer.write("//dont redact this file, it is switcher\nTrue");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getProxy().getPluginManager().registerCommand(this, new CommandEnable("benable","serverjoinblocker", bEnableAliases, dataFolder));
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
            configuration.set("enableMsg", "Joins blocked!");

            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!configuration.contains("disableMsg")){
            configuration.set("disableMsg", "Joins allowed!");
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (!configuration.contains("kickMsg")){
            configuration.set("kickMsg", true);
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
