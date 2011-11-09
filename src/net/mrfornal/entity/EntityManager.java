package net.mrfornal.entity;

import java.util.ArrayList;

/**
 * This singleton class manages all the entities in the game.
 * 
 * All entities should register themselves with the EntityManager.
 * 
 * @author pham266693
 */
public class EntityManager
{
    //A collection of some sort - uses ArrayList here

    private ArrayList<Entity> entities;
    //singleton instance
    private static EntityManager instance;

    private EntityManager()
    {
        entities = new ArrayList<Entity>();
    }

    public ArrayList<Entity> getAllEntities()
    {
        return entities;
    }

    public ArrayList<Entity> getEntitiesOfType(Class c)
    {
        ArrayList<Entity> entityType = new ArrayList<Entity>();
        for (Entity i : entities)
        {
            if (i.getClass().equals(c))
            {
                entityType.add(i);
            }
        }
        return entityType;
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    public Entity getEntity(String name)
    {
        for (Entity entity : entities)
        {
            if (entity.getName().equals(name))
            {
                return entity;
            }
        }
        return null; //Entity does not exist
    }

    public static EntityManager getInstance()
    {
        if (instance == null)
        {
            instance = new EntityManager();
        }
        return instance;
    }
}
