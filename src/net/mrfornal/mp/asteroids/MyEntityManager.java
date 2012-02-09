package net.mrfornal.mp.asteroids;

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
    private ArrayList<RocketEntity> rocketEntities; //contains bullets that do damage and disappear on contact
    private ArrayList<Particle> particles; //contains all particles in the game for recycling
    private ArrayList<ParticleSystem> particleSystems; //contains all systems of particle emitters
    private TreeMap<String, RocketEntity> bulletDefaults; //contains stock bullet definitions to be copied into existence
    //singleton instance
    private static MyEntityManager instance;

    private BlockEntity player; //the player's accessible state
    
    private MyEntityManager()
    {
        entities = new ArrayList<Entity>();
        blockEntities = new ArrayList<BlockEntity>();
        rocketEntities = new ArrayList<RocketEntity>();
        particles = new ArrayList<Particle>();
        particleSystems = new ArrayList<ParticleSystem>();
    }

    public ArrayList<Entity> getAllEntities()
    {
        ArrayList<Entity> entityList = new ArrayList<Entity>();
        entityList.addAll(entities);
        entityList.addAll(blockEntities);
        entityList.addAll(rocketEntities);
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

    public RocketEntity getBulletEntity(String name)
    {
        for (RocketEntity e : rocketEntities)
        {
            if ((e.getName().equals(name)))
            {
                return e;
            }
        }
        //no matching name found
        return null;
    }
    
    public void addParticleSystem(ParticleSystem pS)
    {
        particleSystems.add(pS);
    }

    public ArrayList<ParticleSystem> getAllParticleSystems()
    {
        return particleSystems;
    }
    
    
    
    public void addParticle(Particle p)
    {
        particles.add(p);
    }
    
    public ArrayList<Particle> getAllParticles()
    {
        return particles;
    }
    
    public Particle getUnusedParticle()
    {
        //continuously moves used particles to the end of the particles list
        //unused particles make up the smaller indexes of the list
        Particle p = particles.remove(0);
        particles.add(p);
        return p;
    }

    public ArrayList<BlockEntity> getBlockEntities()
    {
        return blockEntities;
    }

    public void setBlockEntities(ArrayList<BlockEntity> blockEntities)
    {
        this.blockEntities = blockEntities;
    }

    public ArrayList<RocketEntity> getBulletEntities()
    {
        for (int i = 0; i < rocketEntities.size(); i++)
        {
            RocketEntity e = rocketEntities.get(i);
            if (e.deadBullet)
            {
                rocketEntities.remove(i);
            }
        }
        return rocketEntities;
    }

    public void setPlayer(BlockEntity player)
    {
        this.player = player;
    }

    public BlockEntity getPlayer()
    {
        return player;
    }
    
    

    public void setBulletEntities(ArrayList<RocketEntity> bulletEntities)
    {
        this.rocketEntities = bulletEntities;
    }

    public void addBulletEntity(RocketEntity e)
    {
        rocketEntities.add(e);
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
        for (RocketEntity entity : getBulletEntities())
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
