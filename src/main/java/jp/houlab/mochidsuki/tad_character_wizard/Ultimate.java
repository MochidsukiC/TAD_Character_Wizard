package jp.houlab.mochidsuki.tad_character_wizard;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Vector3f;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.config;

public class Ultimate extends BukkitRunnable{
    final private Location location;
    final private Player player;
    final private double pitch;
    final private double yaw;
    private BlockDisplay blockDisplay;
    private int times;
    Ultimate(Location location, Player player){
        this.location = location;
        this.player = player;
        pitch = Math.toRadians(player.getPitch());
        yaw = Math.toRadians(-1 * player.getYaw());
    }
    @Override
    public void run(){
        spawnParticle(location,player,times);
        times++;
    }
    private void spawnParticle(Location location, Player player,int times){
        int r = config.getInt("Ultimate.r");
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
        int prepareTime = config.getInt("Ultimate.prepareTime");
        int runTime = config.getInt("Ultimate.attackTime");
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
        }else {
            if(times <= prepareTime+runTime){
                if(times == prepareTime) {
                    blockDisplay = location.getWorld().spawn(location.clone().add(new Vector(0, 0, 1).rotateAroundZ(Math.toRadians(times)).rotateAroundX(pitch).rotateAroundY(yaw)), BlockDisplay.class);
                }else {
                    if(times <= prepareTime + 10){


                        blockDisplay.teleport(location.clone().add(new Vector(0,0,config.getInt("Ultimate.long")*(times - prepareTime)/10).rotateAroundX(pitch).rotateAroundY(yaw)));
                        blockDisplay.setDisplayWidth((float) (2*config.getDouble("Ultimate.long")*(times - prepareTime)/10));
                        blockDisplay.setRotation((float) yaw, (float) pitch);
                        //blockDisplay.setTransformation(new Transformation(blockDisplay.getTransformation().getTranslation(),blockDisplay.getTransformation().getLeftRotation(),new Vector3f(),blockDisplay.getTransformation().getRightRotation()));
                    }
                }
            }else {
                cancel();
            }
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


