package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.plugin;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tcw")){
            if(strings[0].equalsIgnoreCase("ult")){
                new Ultimate(((Player)sender).getEyeLocation(), ((Player) sender),null).runTaskTimer(plugin,0,1);
            }
            if(strings[0].equalsIgnoreCase("abi")){
                Ability.spawnShield((Player) sender);
            }
            if(strings[0].equalsIgnoreCase("debug")){
                ItemStack itemStack = new ItemStack(Material.STICK);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.addEnchant(Enchantment.LUCK,1,true);
                itemStack.setItemMeta(itemMeta);
                ((Player)sender).getInventory().addItem(itemStack);
            }
        }
        return false;
    }
}
