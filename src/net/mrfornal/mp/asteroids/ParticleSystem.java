/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
//rebuilding particles for custom use
//This class holds particle emitters at a specific location
public class ParticleSystem
{
    private ArrayList<ParticleEmitter> emitters;
    private Vector2f position;
    
    public ParticleSystem(Vector2f position)
    {
        this.position = position;
        emitters = new ArrayList<ParticleEmitter>();
    }
    public ParticleSystem(float px, float py)
    {
        this(new Vector2f(px,py));
    }
    
    public void addEmitter(ParticleEmitter e)
    {
        emitters.add(e);
    }
    
    public void update(GameContainer container)
    {
        for (ParticleEmitter particleEmitter : emitters)
        {
            particleEmitter.update();
        }
    }
    
}
