/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
//rebuilding particles for custom use
//This has a location relative to the particle system
public class ParticleEmitter
{

    //private ArrayList<Particle> particles; //contains currently used particles sent by this emitter
    private Vector2f position;
    MyEntityManager manager = MyEntityManager.getInstance();
    private float fadeSpeed;
    private float maxVelocity;
    Image particleImage;
    private boolean isActive; //true if the emitter is sending particles - does not turn false if max particles are reached
    private int emitRate; //ticks per particle emission
    private int ticker; //set to 0 once it hit emitRate, increments each tick

    public ParticleEmitter()
    {
    }

    public ParticleEmitter(Vector2f p, float fS, float maxV, Image img,int rate)
    {
        //particles = new ArrayList<Particle>();
        position = p;
        fadeSpeed = fS;
        maxVelocity = maxV;
        particleImage = img;
        emitRate = rate;
    }

    public ParticleEmitter(float px, float py, float fS, float maxV, Image img,int rate)
    {
        this(new Vector2f(px, py), fS, maxV, img,rate);

    }

    public Image getParticleImage()
    {
        return particleImage;
    }

    public void setParticleImage(Image particleImage)
    {
        this.particleImage = particleImage;
    }

//    public ArrayList<Particle> getParticles()
//    {
//        return particles;
//    }
//
//    public void setParticles(ArrayList<Particle> particles)
//    {
//        this.particles = particles;
//    }

    public void sendParticle()
    {
        Particle temp = manager.getUnusedParticle();
        if (temp != null)
        {
            /*particles.add*/temp.setVars(fadeSpeed,
                    (float) (maxVelocity * Math.random()-maxVelocity * Math.random())*2,
                    (float) (maxVelocity * Math.random()-maxVelocity * Math.random())*2,
                    position.copy(), particleImage);
        }
    }

    public void rotate(double theta)
    {
        position.setTheta(theta);
    }

    public int getEmitRate()
    {
        return emitRate;
    }

    public void setEmitRate(int emitRate)
    {
        this.emitRate = emitRate;
    }

    public boolean isIsActive()
    {
        return isActive;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }
    public void update()
    {
        if(ticker++ >= emitRate)
        {
            sendParticle();
            ticker = 0;
        }
    }    
}
