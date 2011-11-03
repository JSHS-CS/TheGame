/*
 * Abstract Component class.
 * All Components participate in the update game cycle.
 */
package net.mrfornal.component;

import net.mrfornal.entity.Entity;
import org.newdawn.slick.GameContainer;

/**
 *
 * @author sfornal
 */
public abstract class Component
{

    private Entity owner;
    private String name; //otherwise called ID
    private static int instance_count = 0;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getName()
    {
        return name;
    }

    public Component(Entity owner)
    {
        this.owner = owner;
        this.name = getClass().getSimpleName() + instance_count++;
    }

    public Component(Entity owner, String name)
    {
        this.owner = owner;
        this.name = name;
    }

    /**
     * Get the value of owner
     *
     * @return the value of owner
     */
    public Entity getOwner()
    {
        return owner;
    }
    
    public abstract void update(GameContainer container, int delta);
}
