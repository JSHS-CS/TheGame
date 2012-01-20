/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.Comparator;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
public class DistanceSorter implements Comparator<Targetable>
{
private BlockEntity player = MyEntityManager.getInstance().getPlayer();
    @Override
    public int compare(Targetable o1, Targetable o2)
    {
            Vector2f center1 = new Vector2f(o1.getTarget().getBlock().getCenterX(), o1.getTarget().getBlock().getCenterY());
            Vector2f otherCenter1 = new Vector2f(player.getBlock().getCenterX(), player.getBlock().getCenterY());
            float length1 = center1.sub(otherCenter1).length()-player.getBlock().getWidth()/2-o1.getTarget().getBlock().getWidth()/2;
        
            Vector2f center2 = new Vector2f(o2.getTarget().getBlock().getCenterX(), o2.getTarget().getBlock().getCenterY());
            Vector2f otherCenter2 = new Vector2f(player.getBlock().getCenterX(), player.getBlock().getCenterY());
            float length2 = center2.sub(otherCenter2).length()-player.getBlock().getWidth()/2-o2.getTarget().getBlock().getWidth()/2;
        
        return (int)length1-(int)length2;
    }
}
