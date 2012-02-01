/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;

/**
 *
 * @author pham266693
 */
// based on FireEmitter
public class EngineEmitter implements ParticleEmitter
{

    /** The x coordinate of the center of the effect */
    private int x;
    /** The y coordinate of the center of the effect */
    private int y;
    /** The particle emission rate */
    private int interval = 50;
    /** Time til the next particle */
    private int timer;
    /** The size of the initial particles */
    private float size = 1;

    public EngineEmitter()
    {
    }

    public EngineEmitter(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public EngineEmitter(int x, int y, float size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void updatePosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void update(ParticleSystem system, int delta)
    {
        timer -= delta;
        if (timer <= 0)
        {
            timer = interval;
            Particle p = system.getNewParticle(this, 1000);
            p.setColor(1, 1, 1, 0.5f);
            p.setPosition(x, y);
            p.setSize(size);
            float vx = (float) ( ((-Math.random()+Math.random()) * 0.02f));
            float vy = (float)( ((-Math.random()+Math.random()) * 0.02f));
            p.setVelocity(vx, vy, 1.1f);
        }
    }

    /**
     * @see org.newdawn.slick.particles.ParticleEmitter#updateParticle(org.newdawn.slick.particles.Particle, int)
     */
    public void updateParticle(Particle particle, int delta)
    {
        if (particle.getLife() > 600)
        {
            particle.adjustSize(0.07f * delta);
        } else
        {
            particle.adjustSize(-0.04f * delta * (size / 40.0f));
        }
        float c = 0.002f * delta;
        particle.adjustColor(0, -c / 2, -c * 2, -c / 4);
    }

    @Override
    public boolean completed()
    {
        return false;
    }

    @Override
    public void wrapUp()
    {
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled)
    {
    }

    @Override
    public boolean useAdditive()
    {
        return false;
    }

    @Override
    public Image getImage()
    {
        return null;
    }

    @Override
    public boolean isOriented()
    {
        return false;
    }

    @Override
    public boolean usePoints(ParticleSystem system)
    {
        return false;
    }

    @Override
    public void resetState()
    {
    }
}
