package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.plugin;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tcw")){
            if(strings[0].equalsIgnoreCase("ult")){
                sender.sendMessage("a");
                new Ultimate(((Player)sender).getEyeLocation(), ((Player) sender)).runTaskTimer(plugin,0,1);
            }
            if(strings[0].equalsIgnoreCase("abi")){
                Ability.spawnShield((Player) sender);
            }
        }
        return false;
    }
}
