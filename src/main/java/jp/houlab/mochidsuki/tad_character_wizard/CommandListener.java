package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tcw")){
            if(strings[0].equalsIgnoreCase("ult")){
                sender.sendMessage("a");
                Ultimate.spawnParticle(((Player)sender).getEyeLocation());
            }
        }
        return false;
    }
}
