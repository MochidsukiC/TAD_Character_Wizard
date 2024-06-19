package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.plugin;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (event.getMaterial()) {
                case WILD_ARMOR_TRIM_SMITHING_TEMPLATE: { //マテリアルはあとで決定する
                    Ability.spawnShield(event.getPlayer());
                }
                case STICK:{
                    new Ultimate((event.getPlayer()).getEyeLocation(), (event.getPlayer())).runTaskTimer(plugin,0,1);
                }
            }
        }
    }
}
