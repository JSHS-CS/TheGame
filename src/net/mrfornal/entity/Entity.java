/*
 * The abstract Entity. Try to keep this class limited to data that is not
 * specific to any game, or any particular method of maintaining state or
 * rendering.
 */
package net.mrfornal.entity;

/**
 *
 * @author sfornal
 */
public abstract class Entity
{

    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName()
    {
        return name;
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
}
