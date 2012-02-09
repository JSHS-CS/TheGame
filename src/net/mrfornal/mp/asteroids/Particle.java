/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
//rebuilding particles for custom use
//Image that will fade at a set velocity from ParticleEmitter
public class Particle
{

    private float fadeSpeed;
    private Vector2f velocity, position;
    private Image particleImage;
    private boolean isUsed;
    private float fade; //255

    public Particle()
    {
    }

    public Particle setVars(float fS, Vector2f v, Vector2f p, Image img)
    {
        fadeSpeed = fS;
        velocity = v;
        position = p;
        particleImage = img;
        isUsed = true;
        fade = 255;
        return this;
    }

    public Particle setVars(float fS, float vx, float vy, Vector2f p, Image img)
    {
        return setVars(fS, new Vector2f(vx, vy), p, img);
    }

    public Particle setVars(float fS, float vx, float vy, float px, float py, Image img)
    {
        return setVars(fS, vx, vy, new Vector2f(px, py), img);
    }

    public Vector2f getPosition()
    {
        return position;
    }

    public Vector2f getVelocity()
    {
        return velocity;
    }

    public boolean isUsed()
    {
        return isUsed;
    }

    public void killParticle()
    {
        isUsed = false;
    }

    public void update()
    {
        if (isUsed)
        {
            position.add(velocity);
            fade -= fadeSpeed;
            if (fade <= fadeSpeed)
            {
                killParticle();
            }
            particleImage.setAlpha(fade);
        }
    }

    public void render(GameContainer container, Graphics g)
    {

        if (isUsed)
        {
            g.drawImage(particleImage, position.x, position.y);
        }
    }
}
