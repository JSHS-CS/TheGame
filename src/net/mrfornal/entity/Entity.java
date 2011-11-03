/*
 * The abstract Entity. Try to keep this class limited to data that is not
 * specific to any game, or any particular method of maintaining state or
 * rendering.
 */
package net.mrfornal.entity;

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

    private Vector2f position;
    private String name;
    private float rotation;
    private float scale;
    private int layer;
    //collection of components of the entity
    private ArrayList<Component> components = new ArrayList<Component>();

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

    public void addComponent(Component comp)
    {
        components.add(comp);
    }
//removes and returns a component that is removed

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
