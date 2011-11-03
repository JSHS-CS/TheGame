/*
 * The abstract Entity. Try to keep this class limited to data that is not
 * specific to any game, or any particular method of maintaining state or
 * rendering.
 */
package net.mrfornal.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 */
public abstract class Entity
{

    private Vector2f position;
    private String name;
    private float rotation;
    private float scale;
    private int layer;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName()
    {
        return name;
    }

    public Vector2f getPosition()
    {
        return position;
    }

    public float getScale()
    {
        return scale;
    }

    public float getRotation()
    {
        return rotation;
    }

    public int getLayer()
    {
        return layer;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public void setPosition(Vector2f pos)
    {
        this.position = pos;
    }

    public void setScale(float sc)
    {
        this.scale = sc;
    }

    public void setRoation(float rot)
    {
        this.rotation = rot;
    }

    public void setLayer(int lay)
    {
        this.layer = lay;
    }
    
    public final void update(GameContainer container, int delta) throws SlickException
    {
        //Iterate through collection of Components,
        //calling update on each
    }
    
    public final void init(GameContainer container) throws SlickException
    {
        //Iterate through collection of Components,
        //calling init on each
    }
    
    public final void render(GameContainer container, Graphics g) throws SlickException
    {
        //Iterate through collection of Components,
        //calling render on each
        
        
    }
}
