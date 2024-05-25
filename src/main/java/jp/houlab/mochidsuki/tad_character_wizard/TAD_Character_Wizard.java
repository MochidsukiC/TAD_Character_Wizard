package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

public final class TAD_Character_Wizard extends JavaPlugin {
static public FileConfiguration config;
static public Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic

        //plugin
        plugin = this;

        //config
        saveDefaultConfig();
        config = getConfig();

        //command
        getCommand("tcw").setExecutor(new CommandListener());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
