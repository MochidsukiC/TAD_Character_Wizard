package jp.houlab.mochidsuki.tad_character_wizard;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import static jp.houlab.mochidsuki.tad_character_wizard.TAD_Character_Wizard.config;

public class Ultimate {
    static public void spawnParticle(Location location){
        int r = config.getInt("Ultimate.r");
        double tan72 = Math.tan(Math.toRadians(72));
        double tan36 = Math.tan(Math.toRadians(36));
        double tan18 = Math.tan(Math.toRadians(18));
        int x = (int) (2*r*tan18);

        //fA
        for(int a = -1*x/2; a<x/2;a++){
            int b;
            b = (int) (r - x*Math.sin(Math.toRadians(72) + x*Math.sin(Math.toRadians(36))));
            location.getWorld().spawnParticle(Particle.END_ROD,location.add(new Vector(1,a,b)),3);
        }

        //fB
        for(int a = -1*x/2; a< (double) (-1 * x) /2 + x*Math.cos(Math.toRadians(36)) ; a++){
            int b;
            b = (int) (-1*a*tan36 - (x/2*tan36 - x/2*tan18));
            location.getWorld().spawnParticle(Particle.END_ROD,location.add(new Vector(1,a,b)),3);
        }

        //fC
        for(int a = 0; a< (double) (-1 * x) /2 + x*Math.cos(Math.toRadians(36)) ; a++){
            int b;
            b = (int) (-1*a*tan72 + r);
            location.getWorld().spawnParticle(Particle.END_ROD,location.add(new Vector(1,a,b)),3);
        }

        //fD
        for(int a = (int) (x/2 - x*Math.cos(Math.toRadians(36))); a< 0; a++){
            int b;
            b = (int) (a*tan72 + r);
            location.getWorld().spawnParticle(Particle.END_ROD,location.add(new Vector(1,a,b)),3);
        }
        //fE
        for(int a = (int) (x/2 - x*Math.cos(Math.toRadians(36))); a< x/2; a++){
            int b;
            b = (int) (a*tan36 - (x/2*tan36 - x/2*tan18));
            location.getWorld().spawnParticle(Particle.END_ROD,location.add(new Vector(1,a,b)),3);
        }
        //fF
        for(int a = 0; a<180;a++){
            int b;
            b = (int) (r*Math.tan(Math.toRadians(a)));
            location.getWorld().spawnParticle(Particle.END_ROD,location.add(new Vector(1,a,b)),3);
        }
    }
}
