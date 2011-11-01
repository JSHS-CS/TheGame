/*
 * Abstract Component class.
 * All Components participate in the update game cycle.
 */
package net.mrfornal.component;

import net.mrfornal.entity.Entity;

/**
 *
 * @author sfornal
 */
public class Component
{

    private Entity owner;
    private String id;
    
    private static int instance_count = 0;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId()
    {
        return id;
    }

    public Component(Entity owner)
    {
        this.owner = owner;
        this.id = getClass().getSimpleName() + instance_count++;
    }

    public Component(Entity owner, String id)
    {
        this.owner = owner;
        this.id = id;
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

}
