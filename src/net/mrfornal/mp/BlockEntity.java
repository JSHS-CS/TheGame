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

    public static final float ugc = (float) (6.67 * Math.pow(10, -3));
    protected Vector2f velocity;
    protected Shape block;
    protected float mass;
    protected Vector2f acceleration;
    protected int hp;
    protected int maxHP; //TODO: Put maxhp in a static class that handles block types and their respective maxhp values (HashMap)
    protected Image sprite;

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
                System.out.println(acceleration.x + " " + acceleration.y);
                velocity.add(acceleration);
                //accelerate based on position of other blocks

                //Collision with another block
                collide(e);

            }
        }
        setPosition(getPosition().add(velocity));

        block.setX(getPosition().x);
        block.setY(getPosition().y);

        //prevents from leaving screen

    }

    public void collide(BlockEntity other)
    {
        if (this.getBlock().intersects((other.getBlock())))
        {
            
            //final collision model - gave up, used wikipedia's model for 2d collision in C#.
            
            Vector2f Dn = this.position.copy().sub(other.position);
 
        // The distance between the balls
        float delta = Dn.Length();
 
        // The normal vector of the collision plane
        Dn.Normalize();
 
        // The tangential vector of the collision plane
        Vector2f Dt = new Vector2f(Dn.Y, -Dn.X);
 
        // avoid division by zero
        if (delta == 0)
        {
            ball.Center += new Vector2f(0.01f,0);
            return;
        }
 
        //the masses of the two balls
        float m1 = this.Mass;
        float m2 = ball.Mass;
        float M = m1 + m2;
 
        // minimum translation distance to push balls apart after intersecting
        Vector2f mT = Dn * ((this.radius + ball.Radius - delta));
 
        // push the balls apart proportional to their mass
        this.Center = this.Center + (mT * m2/M);
        ball.Center = ball.Center - (mT * m1/M);
 
        // the velocity vectors of the balls before the collision
        Vector2f v1 = this.velocity;
        Vector2f v2 = ball.velocity;
 
        // split the velocity vector of the first ball into a normal and a tangential component in respect of the collision plane
        Vector2f v1n = Dn * Vector2f.Dot(v1, Dn);
        Vector2f v1t = Dt * Vector2f.Dot(v1, Dt);
 
        // split the velocity vector of the second ball into a normal and a tangential component in respect of the collision plane
        Vector2f v2n = Dn * Vector2f.Dot(v2, Dn);
        Vector2f v2t = Dt * Vector2f.Dot(v2, Dt);
 
        // calculate new velocity vectors of the balls, the tangential component stays the same, the normal component changes analog to the 1-Dimensional case
        this.Velocity = v1t + Dn * ((m1 - m2) / M * v1n.Length() + 2 * m2 / M * v2n.Length());
        ball.velocity = v2t - Dn * ((m2 - m1) / M * v2n.Length() + 2 * m1 / M * v1n.Length());
            
            
            
            
            
            
            
            
            //newer, still imperfect collision
        /*
            
            
            Vector2ff positionCalc = new Vector2ff();
            
            //theta in direction of other circle
            positionCalc.set(new Vector2ff(other.getBlock().getCenterX(), other.getBlock().getCenterY()));
            positionCalc.sub(new Vector2ff(block.getCenterX(), block.getCenterY()));
            
            //calculation
            double theta1 = velocity.getTheta();
            double theta2 = positionCalc.getTheta();
            
            double theta3 = (velocity.getTheta()-positionCalc.getTheta())/3;
            
            velocity.setTheta(theta1 + theta3);
            
            //boost so it doesn't stick
             * 
            position.add(velocity);
             * 
             */
            //new Collision - broken :(
            /*
            //Step 1
            Vector2ff normal = new Vector2ff();
            normal.set(other.position);
            normal.sub(position);
            
            Vector2ff unitNormal = new Vector2ff(
            normal.x / (float) Math.sqrt(Math.pow(normal.x, 2) + Math.pow(normal.y, 2)),
            normal.y / (float) Math.sqrt(Math.pow(normal.x, 2) + Math.pow(normal.y, 2)));
            
            Vector2ff unitTangent = new Vector2ff(-unitNormal.y, unitNormal.x);
            
            //Step 2
            Vector2ff v1n = new Vector2ff(), v1t = new Vector2ff(), v2n = new Vector2ff(), v2t = new Vector2ff();
            v1n.set(unitNormal);
            v1t.set(unitTangent);
            v2n.set(unitNormal);
            v2t.set(unitTangent);
            
            
            //Step 3
            v1n.dot(velocity);
            v1t.dot(velocity);
            v2n.dot(other.velocity);
            v2t.dot(other.velocity);
            //n notation for new
            Vector2ff v1tn = v1t.copy(), v2tn = v2t.copy();
            Vector2ff v1nn = new Vector2ff(), v2nn = new Vector2ff();
            
            //Step 4
            v1tn.set(v1t);
            v2tn.set(v2t);
            
            //Step 5
            float m1 = this.getMass();
            float m2 = other.getMass();
            
            Vector2ff v1nCalc = new Vector2ff(v1n.getX() * (m1 - m2), v1n.getY() * (m1 - m2));
            Vector2ff v1nCalc2 = new Vector2ff(v2n.getX() * (2 * m2), v2n.getY() * (2 * m2));
            v1nCalc.add(v1nCalc2); //does not conflict with pointers
            v1nCalc2 = new Vector2ff(v1nCalc.getX() / (m1 + m2), v1nCalc.getY() / (m1 + m2));
            //f is notation for final
            Vector2ff v1nf = v1nCalc2.copy();
            
            Vector2ff v2nCalc = new Vector2ff(v2n.getX() * (m2 - m1), v2n.getY() * (m2 - m1));
            Vector2ff v2nCalc2 = new Vector2ff(v1n.getX() * (2 * m1), v1n.getY() * (2 * m1));
            v2nCalc.add(v1nCalc2); //does not conflict with pointers
            v2nCalc2 = new Vector2ff(v2nCalc.getX() / (m1 + m2), v2nCalc.getY() / (m1 + m2));
            //f is notation for final
            Vector2ff v2nf = v2nCalc2.copy();
            
            Vector2ff v1tCalc = new Vector2ff(v1t.getX() * (m1 - m2), v1t.getY() * (m1 - m2));
            Vector2ff v1tCalc2 = new Vector2ff(v2t.getX() * (2 * m2), v2t.getY() * (2 * m2));
            v1tCalc.add(v1tCalc2); //does not conflict with pointers
            v1tCalc2 = new Vector2ff(v1tCalc.getX() / (m1 + m2), v1tCalc.getY() / (m1 + m2));
            //f is notation for final
            Vector2ff v1tf = v1tCalc2.copy();
            
            Vector2ff v2tCalc = new Vector2ff(v2t.getX() * (m2 - m1), v2t.getY() * (m2 - m1));
            Vector2ff v2tCalc2 = new Vector2ff(v1t.getX() * (2 * m1), v1t.getY() * (2 * m1));
            v2tCalc.add(v1tCalc2); //does not conflict with pointers
            v2tCalc2 = new Vector2ff(v2tCalc.getX() / (m1 + m2), v2tCalc.getY() / (m1 + m2));
            //f is notation for final
            Vector2ff v2tf = v2tCalc2.copy();
            
            //Step 6
            v1nf = unitNormal.copy().scale(v1nf.length());
            v1tf = unitNormal.copy().scale(v1tf.length());
            v2nf = unitNormal.copy().scale(v2nf.length());
            v2tf = unitNormal.copy().scale(v2tf.length());
            
            //Step 7
            Vector2ff v1f = v1nf.copy().add(v1tf);
            Vector2ff v2f = v2nf.copy().add(v2tf);
            
            velocity = v1f.copy();
            other.setVelocity(v2f.copy());
             */
        }

        //old, imperfect collision
        /*
        if (this.getBlock().intersects(((BlockEntity) e).getBlock()))
        {
        Vector2ff positionCalc = new Vector2ff(block.getCenterX(), block.getCenterY());
        Vector2ff positionCalc2 = new Vector2ff(e.getBlock().getCenterX(), e.getBlock().getCenterY());
        positionCalc.add(positionCalc2);
        double theta2 = positionCalc.getTheta();
        double theta1 = velocity.getTheta();
        velocity.setTheta(theta1 + 2 * theta2);
        }
         */
        //old collision - just reversed velocity vector
        /*
        if (this.getBlock().intersects(((BlockEntity) e).getBlock()))
        {
        velocity.set(-velocity.x, -velocity.y);
        setPosition(getPosition().add(velocity));
        }
         */
    }

    public void edgeCollide(GameContainer container)
    {
        if (container.getWidth() + 50 < block.getMaxX())
        {
            velocity.set(-velocity.x, velocity.y);
        }
        if (container.getHeight() + 50 < block.getMaxY())
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
