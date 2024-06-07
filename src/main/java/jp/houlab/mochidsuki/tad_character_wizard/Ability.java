package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.config;
import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.plugin;

public class Ability {

    static public void spawnShield(Player player){
        double pitch = Math.toRadians(player.getPitch());
        double yaw = Math.toRadians(-1*player.getYaw());
        List<ArmorStand> armorStands = new ArrayList<>();
        List<BlockDisplay> blockDisplays = new ArrayList<>();
        for(int ii = 0;ii< TAD_Character_Wizard.config.getInt("Ability.Health");ii++) {
            for (int i = 0; i < 5; i++) {
                Location location = player.getLocation().clone().add(new Vector(i - 2, 0, 3).rotateAroundY(yaw));
                location.setPitch(0);
                ArmorStand armorStand = player.getWorld().spawn(location, ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStands.add(armorStand);

                for (int iii = 0; iii < 2;iii++){
                    BlockDisplay blockDisplay = location.getWorld().spawn(location.clone().add(new Vector(-0.5,iii,-0.5).rotateAroundY(yaw)), BlockDisplay.class);
                    blockDisplay.setGravity(false);
                    GlassPane blockData = (GlassPane) Bukkit.createBlockData(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                    blockData.setFace(BlockFace.EAST,true);
                    blockData.setFace(BlockFace.WEST,true);
                    blockDisplay.setBlock(blockData);
                    blockDisplay.setBrightness(new Display.Brightness(15,15));
                    blockDisplays.add(blockDisplay);
                }
            }
        }
        new teleportShield(armorStands,blockDisplays,player).runTaskTimer(plugin,0,1);
    }
}

class teleportShield extends BukkitRunnable{
    List<ArmorStand> armorStands;
    List<BlockDisplay> blockDisplays;
    Player player;
    int times;

    public teleportShield(List<ArmorStand> armorStands,List<BlockDisplay> blockDisplays,Player player){
        this.armorStands = armorStands;
        this.blockDisplays = blockDisplays;
        this.player = player;
    }
    @Override
    public void run() {
        int iv = 0;
        int v = 0;
        double yaw = Math.toRadians(-1*player.getYaw());
        for(int ii = 0;ii< TAD_Character_Wizard.config.getInt("Ability.Health");ii++) {
            for (int i = 0; i < 5; i++) {
                Location location = player.getLocation().clone().add(new Vector(i - 2, 0, 3).rotateAroundY(yaw));
                location.setPitch(0);
                ArmorStand armorStand = armorStands.get(iv);
                armorStand.teleport(location);
                iv++;
                for (int iii = 0; iii < 2;iii++){
                    blockDisplays.get(v).teleport(location.clone().add(new Vector(-0.5,iii,-0.5).rotateAroundY(yaw)));
                    v++;
                }
            }
        }

        if(times > config.getInt("Ability.Time")*20){
            for(ArmorStand armorStand : armorStands){
                armorStand.remove();
            }
            for (BlockDisplay blockDisplay : blockDisplays){
                blockDisplay.remove();
            }
            cancel();
        }


    times++;
    }
}