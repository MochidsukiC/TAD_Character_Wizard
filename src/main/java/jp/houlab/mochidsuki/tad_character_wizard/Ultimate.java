package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.config;
import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.plugin;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Ultimate extends BukkitRunnable{
    private Location location;
    private Player player;
    private double pitch;
    private double yaw;
    Ultimate(Location location, Player player){
        this.location = location;
        this.player = player;
        pitch = Math.toRadians(player.getPitch());
        yaw = Math.toRadians(-1 * player.getYaw());
    }
    @Override
    public void run(){
        spawnParticle(location,player);
    }
    private void spawnParticle(Location location, Player player){
        Vector direction = location.getDirection();
        int r = config.getInt("Ultimate.r");
        double tan72 = Math.tan(Math.toRadians(72));
        double tan36 = Math.tan(Math.toRadians(36));
        double tan18 = Math.tan(Math.toRadians(18));
        double x = 2*r*Math.cos(Math.toRadians(18));


        //fA
        for(double a = -1*x/2; a<x/2; a++){
            double b;
            b =r*Math.sin(Math.toRadians(18));
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fB
        for(double a = -1*x/2; a< (double) (-1 * x) /2 + x*Math.cos(Math.toRadians(36)) ; a++){
            double b;
            b = -1*a*tan36 - (x/2*tan36 - x/2*tan18);
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fC
        for(double a = 0; a< (double) (-1 * x) /2 + x*Math.cos(Math.toRadians(36)) ; a++){
            double b;
            b = -1*a*tan72 + r;
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fD
        for(double a = (int) (x/2 - x*Math.cos(Math.toRadians(36))); a< 0; a++){
            double b;
            b = a*tan72 + r;
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fE
        for(double a = x/2 - x*Math.cos(Math.toRadians(36)); a< x/2; a++){
            double b;
            b = (int) (a*tan36 - (x/2*tan36 - x/2*tan18));
            location.getWorld().spawnParticle(Particle.END_ROD,location.clone().add(new Vector(a/10,b/10,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
        }

        //fF
        for(double a = 0; a<360;a = a + 2){
            double b;
            double c;
            c = r*Math.cos(Math.toRadians(a));
            b = r*Math.sin(Math.toRadians(a));
            Location locationTemp = location.clone();
            location.getWorld().spawnParticle(Particle.END_ROD,locationTemp.clone().add(new Vector(c/10,b/10,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
            location.getWorld().spawnParticle(Particle.END_ROD,locationTemp.clone().add(new Vector(c/8,b/8,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);
            location.getWorld().spawnParticle(Particle.END_ROD,locationTemp.clone().add(new Vector(c/40,b/40,1).rotateAroundX(pitch).rotateAroundY(yaw)),3,0,0,0,0);

        }
    }
}


