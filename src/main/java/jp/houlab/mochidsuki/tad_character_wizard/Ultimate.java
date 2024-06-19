package jp.houlab.mochidsuki.tad_character_wizard;

import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import java.util.HashMap;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.config;
import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.plugin;

public class Ultimate extends BukkitRunnable{
    final private Location location;
    final private Player player;
    final private double pitch;
    final private double yaw;
    private int times;
    public Ultimate(Location location, Player player){
        this.location = location;
        this.player = player;
        pitch = Math.toRadians(player.getPitch());
        yaw = Math.toRadians(-1 * player.getYaw());
        location.getWorld().playSound(location,Sound.ENTITY_WITHER_SPAWN,100,1);
    }
    @Override
    public void run(){
        spawnParticle(location,player,times);
        if(times < config.getDouble("Ultimate.prepareTime")) {
            float a = (float) (times / config.getDouble("Ultimate.prepareTime"));
            location.getWorld().playSound(location, Sound.ENTITY_WITHER_SPAWN, 0.3f, a);
        } else if (times == config.getDouble("Ultimate.prepareTime")) {
            //location.getWorld().stopSound(SoundStop.named(Sound.ENTITY_WITHER_SPAWN));
            //location.getWorld().playSound(location, Sound.ENTITY_WITHER_SPAWN, 1, 1);
        }
        times++;
    }
    private void spawnParticle(Location location, Player player,int times){
        final int r = config.getInt("Ultimate.r");
        double tan72 = Math.tan(Math.toRadians(72));
        double tan36 = Math.tan(Math.toRadians(36));
        double tan18 = Math.tan(Math.toRadians(18));
        double x = 2*r*Math.cos(Math.toRadians(18));


        //fA
        for(double a = -1*x/2; a<x/2; a++){
            double b;
            b =r*Math.sin(Math.toRadians(18));
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fB
        for(double a = -1*x/2; a< (-1 * x) /2 + x*Math.cos(Math.toRadians(36)) ; a++){
            double b;
            b = -1*a*tan36 - (x/2*tan36 - x/2*tan18);
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fC
        for(double a = 0; a< (-1 * x) /2 + x*Math.cos(Math.toRadians(36)) ; a++){
            double b;
            b = -1*a*tan72 + r;
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fD
        for(double a = (int) (x/2 - x*Math.cos(Math.toRadians(36))); a< 0; a++){
            double b;
            b = a*tan72 + r;
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fE
        for(double a = x/2 - x*Math.cos(Math.toRadians(36)); a< x/2; a++){
            double b;
            b = (int) (a*tan36 - (x/2*tan36 - x/2*tan18));
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fF
        for(double a = 0; a<360;a = a + 2){
            double b;
            double c;
            c = r*Math.cos(Math.toRadians(a));
            b = r*Math.sin(Math.toRadians(a));
            Location locationTemp = location.clone();
            location.getWorld().spawnParticle(Particle.END_ROD,locationTemp.clone().add(new Vector(c/10,b/10,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
            location.getWorld().spawnParticle(Particle.END_ROD,locationTemp.clone().add(new Vector(c/8,b/8,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
            location.getWorld().spawnParticle(Particle.END_ROD,locationTemp.clone().add(new Vector(c/40,b/40,1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
            
        }
        final int prepareTime = config.getInt("Ultimate.prepareTime");
        final int runTime = config.getInt("Ultimate.attackTime");
        if(times < prepareTime){
            if(times <= prepareTime/5){
                spawnCircleParticle(Particle.DUST_COLOR_TRANSITION,times*10,r,9,new Particle.DustTransition(Color.BLUE,Color.AQUA,2));
            } else if (times <= prepareTime*2/5) {
                spawnCircleParticle(Particle.DUST_COLOR_TRANSITION,times*10,r,9,new Particle.DustTransition(Color.AQUA,Color.GREEN,2));
            } else if (times <= prepareTime*3/5) {
                spawnCircleParticle(Particle.DUST_COLOR_TRANSITION,times*10,r,9,new Particle.DustTransition(Color.GREEN,Color.YELLOW,2));
            } else if (times <= prepareTime*4/5) {
                spawnCircleParticle(Particle.DUST_COLOR_TRANSITION,times*10,r,9,new Particle.DustTransition(Color.YELLOW,Color.ORANGE,2));
            }else {
                spawnCircleParticle(Particle.DUST_COLOR_TRANSITION,times*10,r,9,new Particle.DustTransition(Color.ORANGE,Color.RED,2));
            }
        } else if (times == prepareTime) {
            new ControlUltBlockDisplay(0,location,pitch,yaw,r,runTime).runTaskTimer(plugin,0,1);

        } else if (times>=prepareTime) {
            for(Player player1 : player.getServer().getOnlinePlayers()) {
                double distance = r/10 + 30;
                for (int i = 0; i < config.getInt("Ultimate.long"); i++) {
                    Location location1 = location.clone().add(new Vector(0, 0, i).rotateAroundX(pitch).rotateAroundY(yaw));
                    if(distance > player1.getLocation().distance(location1)){
                        distance = player1.getLocation().distance(location1);
                    }
                }
                player1.playSound(player1,Sound.ENTITY_WITHER_SPAWN,SoundCategory.MASTER, (float) (1-distance/(r/10+10)),4f);
                //player1.playSound(player1,Sound.ENTITY_ENDER_DRAGON_AMBIENT,SoundCategory.MASTER, (float) (1-distance/(r/10+10)),2f);

            }
        }

        if(times > prepareTime+runTime) {
            cancel();
        }
        }

    private void spawnCircleParticle(Particle particle,int k,int r,int scale){
        double b;
        double c;
        c = r*Math.cos(Math.toRadians(k));
        b = r*Math.sin(Math.toRadians(k));
        Location locationTemp = location.clone();
        location.getWorld().spawnParticle(particle,locationTemp.clone().add(new Vector(c/scale,b/scale,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
    }
    private void spawnCircleParticle(Particle particle, int k, int r, int scale, Particle.DustOptions options){
        double b;
        double c;
        for(int a = 0; a < 10;a++) {
            c = r * Math.cos(Math.toRadians(k+a));
            b = r * Math.sin(Math.toRadians(k+a));
            Location locationTemp = location.clone();
            location.getWorld().spawnParticle(particle, locationTemp.clone().add(new Vector(c / scale, b / scale, 1).rotateAroundX(pitch).rotateAroundY(yaw)), 3, 0, 0, 0, 0, options);
        }
    }
}

class ControlUltBlockDisplay extends BukkitRunnable{

    private int times;
    private int alltimes;
    private BlockDisplay blockDisplay;
    private Location location;
    private int k;
    private double pitch;
    private double yaw;
    private int r;
    private int attackTime;
    ControlUltBlockDisplay(int allTimes,Location location,double pitch,double yaw,int r,int attackTime){
        alltimes = allTimes;
        this.location = location;
        this.k = 3;
        this.pitch = pitch;
        this.yaw = yaw;
        this.r = r;
        this.attackTime = attackTime;



        blockDisplay = location.getWorld().spawn(location.clone().add(new Vector(-1*(r/10),(r/10), 1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)), BlockDisplay.class);
        Transformation transformation = blockDisplay.getTransformation();
        transformation.getScale().set(2D);
        transformation.getLeftRotation().rotateLocalX((float) Math.toRadians(90));
        blockDisplay.setTransformation(transformation);
        blockDisplay.setBlock(Bukkit.createBlockData(Material.DRAGON_EGG));
    }

    @Override
    public void run() {
        double l = config.getDouble("Ultimate.long");
        double lNow = 0;
        if(times <= 2*k){
            Transformation transformation = blockDisplay.getTransformation();
            lNow = (l/2)*(times)/(2*k);
            transformation.getScale().set(r/5,lNow,r/5);
            blockDisplay.setTransformation(transformation);
            blockDisplay.setBrightness(new Display.Brightness(15,15));

            if(times== k && alltimes - 6*k <attackTime){
                new ControlUltBlockDisplay(alltimes,location,pitch,yaw,r,attackTime).runTaskTimer(plugin,0,1);
            }
        }else {
            if(times < 6*k){
                blockDisplay.teleport(location.clone().add(new Vector(-1*(r/10),(r/10),(times-2*k)*(l/2)/(2*k)+1).rotateAroundX(pitch).rotateAroundY(yaw)));
                if(times >= 4*k){
                    Transformation transformation = blockDisplay.getTransformation();
                    lNow = (l/2)*(6*k - times)/(2*k);
                    transformation.getScale().set(r/5,lNow,r/5);
                    blockDisplay.setTransformation(transformation);
                }
            }else {

                blockDisplay.remove();
                cancel();
            }
        }
        Location killerCore = location.clone().add(new Vector(0,-1, 1+r/10).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw));
        for(int i = 0;i<lNow - r/10;i++){
            Location location1 = killerCore.clone().add(new Vector(0,0, i).rotateAroundX(pitch).rotateAroundY(yaw));
            for(Player player : plugin.getServer().getOnlinePlayers()){
                if(player.getLocation().distance(location1)<r/10) {
                    player.damage(20);
                }
            }
        }
        times++;
        alltimes++;
    }

}


