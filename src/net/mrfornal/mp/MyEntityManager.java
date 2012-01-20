package net.mrfornal.mp;

import net.mrfornal.entity.*;
import java.util.ArrayList;
import java.util.TreeMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

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

    private ArrayList<Entity> entities; //contains miscellaneous entities
    private ArrayList<BlockEntity> blockEntities; //contains anything with a shape that isn't bullet
    private ArrayList<BulletEntity> bulletEntities; //contains bullets that do damage and disappear on contact
    private TreeMap<String, BulletEntity> bulletDefaults; //contains stock bullet definitions to be copied into existence
    //singleton instance
    private static MyEntityManager instance;

    private BlockEntity player; //the player's accessible state
    
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
        for (int i = 0; i < bulletEntities.size(); i++)
        {
            BulletEntity e = bulletEntities.get(i);
            if (e.deadBullet)
            {
                bulletEntities.remove(i);
            }
        }
        return bulletEntities;
    }

    public void setPlayer(BlockEntity player)
    {
        this.player = player;
    }

    public BlockEntity getPlayer()
    {
        return player;
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
    //rather than bouncing the block off of the edge, make it gradually come back in
    //sort of an elastic slingshot effect
    Vector2f boundaryAccelerationX = new Vector2f(.2f, .0f);
    Vector2f boundaryAccelerationY = new Vector2f(.0f, .2f);

    public void checkBoundaries(GameContainer container)
    {
        for (BlockEntity entity : getBlockEntities())
        {
            Vector2f velocity = entity.getVelocity();
            Shape block = entity.getBlock();
            if (container.getWidth() + AsteroidsGame.BOUNDARY < block.getMaxX())
            {
                velocity.add(boundaryAccelerationX.negate());
                if (velocity.length() > 2.5f)
                {
                    velocity.scale(0.99f);
                }
            }
            if (container.getHeight() + AsteroidsGame.BOUNDARY < block.getMaxY())
            {
                velocity.add(boundaryAccelerationY.negate());
                if (velocity.length() > 2.5f)
                {
                    velocity.scale(0.99f);
                }
            }
            if (-AsteroidsGame.BOUNDARY > block.getMinX())
            {
                velocity.add(boundaryAccelerationX);
                if (velocity.length() > 2.5f)
                {
                    velocity.scale(0.99f);
                }
            }
            if (-AsteroidsGame.BOUNDARY > block.getMinY())
            {
                velocity.add(boundaryAccelerationY);
                if (velocity.length() > 2.5f)
                {
                    velocity.scale(0.99f);
                }
            }
        }
        for (BulletEntity entity : getBulletEntities())
        {
            Vector2f position = entity.getPosition();
            if (container.getWidth() + AsteroidsGame.BOUNDARY < position.x || container.getHeight() + AsteroidsGame.BOUNDARY < position.y)
            {
                entity.deadBullet = true;
            }
            if (-AsteroidsGame.BOUNDARY > position.x || -AsteroidsGame.BOUNDARY > position.y)
            {
                entity.deadBullet = true;
            }
        }
    }
}
