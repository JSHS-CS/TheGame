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

    private static int instance_count = 0;
    protected Vector2f position;
    protected String name;
    protected float rotation;
    protected float scale;
    protected int layer;

    /**
     * Default constructor assigns a consecutively numbered id value
     * as the Entity's name
     */
    public Entity()
    {
        this(null);
    }

    public Entity(String name)
    {
        this.name = (name == null)
                ? (getClass().getSimpleName() + instance_count++) : name;
    }

    /**
     * Get the entity's name
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the Vector2f position
     *
     * @return the value of position
     */
    public Vector2f getPosition()
    {
        return position;
    }

    /**
     * Get the value of scale
     *
     * @return the scale
     */
    public float getScale()
    {
        return scale;
    }

    /**
     * Get the rotation
     *
     * @return the rotation
     */
    public float getRotation()
    {
        return rotation;
    }

    /**
     * Get the z-index for rendering
     *
     * @return the layer
     */
    public int getLayer()
    {
        return layer;
    }

    /**
     * Sets the entity's name
     * @param name The name of the entity
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Set the position Vector2f
     *
     * @param pos new value of position
     */
    public void setPosition(Vector2f pos)
    {
        this.position = pos;
    }

    /**
     * Set the scale
     *
     * @param scale new value of scale
     */
    public void setScale(float sc)
    {
        this.scale = sc;
    }

    /**
     * Set the rotation
     *
     * @param rotation new value of rotation
     */
    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    /**
     * Set the z-index for rendering
     *
     * @param layer new value of layer
     */
    public void setLayer(int layer)
    {
        this.layer = layer;
    }

    /**
     * This method corresponds to the update() method in the Game interface
     * @see org.newdawn.slick.Game
     * @param container
     * @param delta
     * @throws SlickException
     */
    public abstract void update(GameContainer container, int delta) throws SlickException;

    /**
     * This method corresponds to the init() method in the Game interface
     * @see org.newdawn.slick.Game
     * @param container
     * @throws SlickException
     */
    public abstract void init(GameContainer container) throws SlickException;
    
    /**
     * This method corresponds to the render() method in the Game interface
     * @see org.newdawn.slick.Game
     * @param container
     * @param g
     * @throws SlickException
     */
    public abstract void render(GameContainer container, Graphics g) throws SlickException;
}
