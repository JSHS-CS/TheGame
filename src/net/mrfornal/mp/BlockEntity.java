/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
public class BlockEntity extends Entity
{
    // G the Universal Gravitational Constant (6.67x10-11 N.m2/kg2), 
    // It is scaled up to increase gravitational attraction

    public static final float ugc = (float) (6.67 * Math.pow(10, -5));
    protected Vector2f velocity;
    protected Shape block;
    protected float mass;
    protected Vector2f acceleration;
    protected int hp;
    protected int maxHP; //TODO: Put maxhp in a static class that handles block types and their respective maxhp values (HashMap)
    protected Image sprite;

    //default - do not use
    public BlockEntity()
    {
        this(null, "", 0, 0, 0, 0, 0, 1, null);
    }

    public BlockEntity(Shape s, String name, float mass)
    {
        this(s, name, mass, 0, 0);
    }

    public BlockEntity(Shape s, String name, float mass, float x, float y)
    {
        this(s, name, mass, x, y, 0, 0);
    }

    public BlockEntity(Shape s, String name, float mass, float x, float y, float vX, float vY)
    {
        this(s, name, mass, x, y, vX, vY, 100, null);
    }

    public BlockEntity(Shape s, String name, float mass, float x, float y, float vX, float vY, int maxHP, Image spr)
    {
        super(name);
        position = new Vector2f(x, y);
        velocity = new Vector2f(vX, vY);
        block = s;
        this.mass = mass;
        block.setX(position.x);
        block.setY(position.y);
        acceleration = new Vector2f();
        this.maxHP = maxHP;
        hp = maxHP;
        sprite = spr;
    }
    
    public Vector2f getCenterPosition()
    {
        return new Vector2f(block.getCenterX(), block.getCenterY());
    }

    public void addToManager()
    {
        MyEntityManager.getInstance().addBlockEntity(this);
    }

    public Vector2f getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2f velocity)
    {
        this.velocity = velocity;
    }

    public Shape getBlock()
    {
        return block;
    }

    public void setBlock(Shape block)
    {
        this.block = block;
    }

    public float getMass()
    {
        return mass;
    }

    public void setMass(float mass)
    {
        this.mass = mass;
    }

    public Vector2f getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(Vector2f acceleration)
    {
        this.acceleration = acceleration;
    }

    public void takeDamage(int dmg)
    {
        hp -= dmg;
    }

    public void restoreHP(int restore)
    {
        hp += restore;
        if (maxHP < restore)
        {
            hp = maxHP;
        }
    }

    public void restoreAllHP()
    {
        hp = maxHP;
    }

    public int getHP()
    {
        return hp;
    }

    /*
     * If F is the force due to gravity, 
     * g the acceleration due to gravity, 
     * G the Universal Gravitational Constant (6.67x10-11 N.m2/kg2), 
     * m the mass and r the distance between two objects. 
     * Then F = G*m1*m2/r^2
     * 
     * F = m1*a
     * 
     */
    //The important part!
    //======================================================================
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        //code to remove if HP is equal to or under 0 is in BulletEntity
        
//        ArrayList<Entity> list = MyEntityManager.getInstance().getEntitiesOfType(getClass());
        ArrayList<BlockEntity> list = MyEntityManager.getInstance().getBlockEntities();

        updateMovement(container, list);
    }

    //======================================================================
    public void updateMovement(GameContainer container, ArrayList<BlockEntity> list)
    {
        //prevents from leaving screen
        //moved to MyEntityManager
//        edgeCollide(container);

        for (BlockEntity e : list)
        {
            if (!e.equals(this) && e.getMass() > 50)
            {

                float x1 = /*this.getPosition().x + */ this.getBlock().getCenterX(); //turns out getCenter gives you the absolute position
                float y1 = /*this.getPosition().y + */ this.getBlock().getCenterY();
                float x2 = /*e.getPosition().x + */ e.getBlock().getCenterX();
                float y2 = /*e.getPosition().y + */ e.getBlock().getCenterY();
                float m2 = e.getMass();

                //old acceleration - x and y - messed up when both objects were near each other. Needed more refinement.

//                float accX =  ugc * m2 / (x2 - x1);
//                float accY =  ugc * m2 / (y2 - y1);

                float acc = (float) (ugc * m2 / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

                double theta = Math.atan(Math.abs((y2 - y1) / (x2 - x1)));

                float accX = Math.abs(acc * (float) Math.cos(theta));
                float accY = Math.abs(acc * (float) Math.sin(theta));

                //if the other object is to the left of this object, move negative x
                if (x2 < x1)
                {
                    accX *= -1;
                }
                //if the other object is above this object, move negative y?
                if (y2 < y1)
                {
                    accY *= -1;
                }

                acceleration.set(new Vector2f(accX, accY));
                //System.out.println(acceleration.x + " " + acceleration.y);
                velocity.add(acceleration);
                //accelerate based on position of other blocks

                //Collision with another block
                collide(e);

            }
        }
        setPosition(getPosition().add(velocity));

        block.setX(getPosition().x);
        block.setY(getPosition().y);
    }

    public void collide(BlockEntity other)
    {
        if (this.getBlock().intersects((other.getBlock())))
        {
            Vector2f center = new Vector2f(this.getBlock().getCenterX(), this.getBlock().getCenterY());
            Vector2f otherCenter = new Vector2f(other.getBlock().getCenterX(), other.getBlock().getCenterY());

            //final collision model - gave up, used wikipedia's model for 2d collision in C#.

            Vector2f Dn = center.copy().sub(otherCenter);

            // The distance between the balls
            float delta = Dn.length();

            // The normal vector of the collision plane
            Dn.normalise();

            // The tangential vector of the collision plane
            Vector2f Dt = new Vector2f(Dn.y, -Dn.x);

            //the masses of the two balls
            float m1 = this.mass;
            float m2 = other.mass;
            float M = m1 + m2;

            // minimum translation distance to push balls apart after intersecting
            Vector2f mT = Dn.copy().scale((this.getBlock().getWidth() / 2 + other.getBlock().getWidth() / 2 - delta));

            // push the balls apart proportional to their mass
            this.position.add(mT.scale(m2 / M));
            other.position.add(mT.scale(m1 / M));

            // the velocity vectors of the balls before the collision
            Vector2f v1 = this.velocity;
            Vector2f v2 = other.velocity;

            // split the velocity vector of the first ball into a normal and a tangential component in respect of the collision plane
            Vector2f v1n = Dn.copy().scale(v1.dot(Dn));
            Vector2f v1t = Dt.copy().scale(v1.dot(Dt));

            // split the velocity vector of the second ball into a normal and a tangential component in respect of the collision plane
            Vector2f v2n = Dn.copy().scale(v2.dot(Dn));
            Vector2f v2t = Dt.copy().scale(v2.dot(Dt));

            // calculate new velocity vectors of the balls, the tangential component stays the same, the normal component changes analog to the 1-Dimensional case
            this.velocity = v1t.copy().add(Dn.copy().scale((m1 - m2) / M * v1n.length() + 2 * m2 / M * v2n.length()));
            other.velocity = v2t.copy().sub(Dn.copy().scale(((m2 - m1) / M * v2n.length() + 2 * m1 / M * v1n.length())));
        }

    }

    //obsolete method
    /*
    public void edgeCollide(GameContainer container)
    {
    if (container.getWidth() + AsteroidsGame.BOUNDARY < block.getMaxX())
    {
    velocity.set(-velocity.x, velocity.y);
    }
    if (container.getHeight() + AsteroidsGame.BOUNDARY < block.getMaxY())
    {
    velocity.set(velocity.x, -velocity.y);
    }
    if (-AsteroidsGame.BOUNDARY > position.x)
    {
    velocity.set(-velocity.x, velocity.y);
    }
    if (-AsteroidsGame.BOUNDARY > position.y)
    {
    velocity.set(velocity.x, -velocity.y);
    }
    }
     */
    @Override
    public void init(GameContainer container) throws SlickException
    {
        //nothing?
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        g.draw(block);
        if (sprite != null)
        {
            g.drawImage(sprite, position.x, position.y);
        }
        g.drawString("" + hp, getPosition().x, getPosition().y);
    }

    @Override
    public String toString()
    {
        return "BlockEntity{" + "block=(" + (int) (block.getX()) + ", " + (int) (block.getY()) + ")}";
    }
}
