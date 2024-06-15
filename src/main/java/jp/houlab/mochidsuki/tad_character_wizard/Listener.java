package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void EntitySpawnEvent(EntitySpawnEvent event){
        if(event.getEntityType() == EntityType.ARROW){
            V.arrows.add((Arrow) event.getEntity());
        }
    }
}
