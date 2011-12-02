package net.mrfornal.mp;

import net.mrfornal.entity.*;
import java.util.ArrayList;

/**
 * This singleton class manages all the entities in the game.
 * 
 * All entities should register themselves with the EntityManager.
 * 
 * @author pham266693
 */
public class MyEntityManager
{
    //A collection of some sort - uses ArrayList here

    private ArrayList<Entity> entities;
    private ArrayList<BlockEntity> blockEntities;
    private ArrayList<BulletEntity> bulletEntities;
    //singleton instance
    private static MyEntityManager instance;

    private MyEntityManager()
    {
        entities = new ArrayList<Entity>();
        blockEntities = new ArrayList<BlockEntity>();
        bulletEntities = new ArrayList<BulletEntity>();
    }

    public ArrayList<Entity> getAllEntities()
    {
        ArrayList<Entity> entityList = new ArrayList<Entity>();
        entityList.addAll(entities);
        entityList.addAll(blockEntities);
        entityList.addAll(bulletEntities);
        return entityList;
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

    public Entity getBlockEntity(String name)
    {
        for (BlockEntity entity : blockEntities)
        {
            if (entity.getName().equals(name))
            {
                return entity;
            }
        }
        return null; //Entity does not exist
    }

    public void addBlockEntity(BlockEntity e)
    {
        blockEntities.add(e);
    }

    public BulletEntity getBulletEntity(String name)
    {
        for (BulletEntity e : bulletEntities)
        {
            if ((e.getName().equals(name)))
            {
                return e;
            }
        }
        //no matching name found
        return null;
    }

    public ArrayList<BlockEntity> getBlockEntities()
    {
        return blockEntities;
    }

    public void setBlockEntities(ArrayList<BlockEntity> blockEntities)
    {
        this.blockEntities = blockEntities;
    }

    public ArrayList<BulletEntity> getBulletEntities()
    {
        return bulletEntities;
    }

    public void setBulletEntities(ArrayList<BulletEntity> bulletEntities)
    {
        this.bulletEntities = bulletEntities;
    }

    public void addBulletEntity(BulletEntity e)
    {
        bulletEntities.add(e);
    }

    public static MyEntityManager getInstance()
    {
        if (instance == null)
        {
            instance = new MyEntityManager();
        }
        return instance;
    }
}
