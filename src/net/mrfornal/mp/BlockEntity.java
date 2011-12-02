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

    public static final float ugc = (float) (6.67 * Math.pow(10, -3));
    protected Vector2f velocity;
    protected Shape block;
    protected float mass;
    protected Vector2f acceleration;
    protected int hp;
    protected int maxHP; //TODO: Put maxhp in a static class that handles block types and their respective maxhp values (HashMap)

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
        this(s, name, mass, x, y, vX, vY, 100);
    }
    
    public BlockEntity(Shape s, String name, float mass, float x, float y, float vX, float vY, int maxHP)
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
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
//        ArrayList<Entity> list = MyEntityManager.getInstance().getEntitiesOfType(getClass());
        ArrayList<BlockEntity> list = MyEntityManager.getInstance().getBlockEntities();
        
        for (BlockEntity e : list)
        {
            if (!e.equals(this) && e.getMass() > 50)
            {
                
                float x1 = /*this.getPosition().x + */ this.getBlock().getCenterX();
                float y1 = /*this.getPosition().y + */ this.getBlock().getCenterY();
                float x2 = /*e.getPosition().x + */ e.getBlock().getCenterX();
                float y2 = /*e.getPosition().y + */ e.getBlock().getCenterY();
                float m2 = e.getMass();

                //old acceleration - x and y - messed up when both objects were near each other

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
                System.out.println(acceleration.x + " " + acceleration.y);
                velocity.add(acceleration);
                //accelerate based on position of other blocks



                //Collision with another block
                
                
                if (this.getBlock().intersects(((BlockEntity) e).getBlock()))
                {
                    Vector2f positionCalc = new Vector2f(block.getCenterX(), block.getCenterY());
                    Vector2f positionCalc2 = new Vector2f(e.getBlock().getCenterX(), e.getBlock().getCenterY());
                    positionCalc.add(positionCalc2);
                    double theta2 = positionCalc.getTheta();
                    double theta1 = velocity.getTheta();
                    velocity.setTheta(theta1 + 2 * theta2);
                }

                //old collision - just reversed velocity vector
                /*
                if (this.getBlock().intersects(((BlockEntity) e).getBlock()))
                {
                velocity.set(-velocity.x, -velocity.y);
                setPosition(getPosition().add(velocity));
                }
                 */
                
            }
        }
        setPosition(getPosition().add(velocity));
        
        block.setX(getPosition().x);
        block.setY(getPosition().y);

        //prevents from leaving screen
        if (container.getWidth() + 50 < position.x)
        {
            velocity.set(-velocity.x, velocity.y);
        }
        if (container.getHeight() + 50 < position.y)
        {
            velocity.set(velocity.x, -velocity.y);
        }
        if (-50 > position.x)
        {
            velocity.set(-velocity.x, velocity.y);
        }
        if (-50 > position.y)
        {
            velocity.set(velocity.x, -velocity.y);
        }
        
        
        
    }
    
    @Override
    public void init(GameContainer container) throws SlickException
    {
        //nothing?
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        g.draw(block);
        g.drawString("" + hp, getPosition().x, getPosition().y);
    }
    
    @Override
    public String toString()
    {
        return "BlockEntity{" + "block=(" + (int) (block.getX()) + ", " + (int) (block.getY()) + ")}";
    }
}
