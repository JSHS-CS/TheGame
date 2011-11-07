/*
 * The abstract Entity. Try to keep this class limited to data that is not
 * specific to any game, or any particular method of maintaining state or
 * rendering.
 */
package net.mrfornal.entity;

import java.util.List;
import java.util.ArrayList;
import net.mrfornal.component.Component;
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
    private Vector2f position;
    private String name;
    private float rotation;
    private float scale;
    private int layer;
    private List<Component> components;

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
        components = new ArrayList<Component>();
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
     * Get the Vector2f postion
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
    public void setRoation(float rotation)
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
     * Add a component to this entity
     * @param comp 
     */
    public void addComponent(Component comp)
    {
        components.add(comp);
    }

    /**
     * Removes a component from this entity
     * @param name The name of the component to remove
     * @return The removed component, or null if no matching name was found
     */
    public Component removeComponent(String name)
    {
        for (int i = 0; i < components.size(); i++)
        {
            if (components.get(i).getName().equals(name))
            {
                return components.remove(i);
            }
        }
        return null;
    }

    /**
     * This method corresponds to the update() method in the Game interface
     * @see org.newdawn.slick.Game
     * @param container
     * @param delta
     * @throws SlickException
     */
    public final void update(GameContainer container, int delta) throws SlickException
    {
        //Iterate through collection of Components,
        //calling update on each

        customUpdate(container, delta);
    }

    /**
     * This method corresponds to the init() method in the Game interface
     * @see org.newdawn.slick.Game
     * @param container
     * @throws SlickException
     */
    public final void init(GameContainer container) throws SlickException
    {
        //Iterate through collection of Components,
        //calling init on each

        customInit(container);
    }

    /**
     * This method corresponds to the render() method in the Game interface
     * @see org.newdawn.slick.Game
     * @param container
     * @param g
     * @throws SlickException
     */
    public final void render(GameContainer container, Graphics g) throws SlickException
    {
        //Iterate through collection of Components,
        //calling render on each RenderableComponent

        customRender(container, g);
    }

    /**
     * Allows a subclass to override and provide custom update functionality
     * @param container
     * @param delta
     */
    protected void customUpdate(GameContainer container, int delta)
    {
        //Override in subclasses to allow an Entity to do something in the update cycle
    }

    /**
     * Allows a subclass to override and provide custom init functionality
     * @param container
     */
    protected void customInit(GameContainer container)
    {
        //Override in subclasses to allow an Entity to do something in the init cycle
    }

    /**
     * Allows a subclass to override and provide custom render functionality
     * @param container
     * @param g
     */
    protected void customRender(GameContainer container, Graphics g)
    {
        //Override in subclasses to allow an Entity to do something in the render cycle
    }
}
