/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import net.mrfornal.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
public class BulletEntity extends Entity
{

    private float initSpeed; //not actually given a value; used to hold data in stock bullets
    private int damage; //TODO: Put in a static class that handles bullet types and their assigned damage values (TreeMap)
    private Vector2f velocity;
    private String originBlock;
    public boolean deadBullet = false; //if true, remove from game

    public BulletEntity()
    {
        super("bullet");
    }

    public BulletEntity(float initS, int dmg, Vector2f v, Vector2f pos, double theta, String origin, float initDistance)
    {
        super("bullet");
        velocity = new Vector2f();
        damage = dmg;
        velocity.set(v);
        Vector2f temp = new Vector2f(initS, 0);
        temp.setTheta(theta);
        velocity.add(temp); //boost in speed for bullet to fire

        Vector2f newPos = new Vector2f(initDistance, 0); //pixel radius of bullet firing
        newPos.setTheta(theta);
        setPosition(pos.add(newPos)); //boost in position so bullet travels past player block
        originBlock = origin; //the name of the block the bullet spawned from
    }

    public void addToManager()
    {
        MyEntityManager.getInstance().addBulletEntity(this);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        ArrayList<BlockEntity> temp = MyEntityManager.getInstance().getBlockEntities();
        ArrayList<BulletEntity> temp2 = MyEntityManager.getInstance().getBulletEntities();

        /*
        int index = 0;
        //DONE: Seems like a really inefficient way to get the bullet's index -pham266693
        for (int i = 0; i < temp2.size(); i++)
        {
        if (temp2.get(i).equals(this))
        {
        index = i;
        }
        }
         */

        //removes if off screen - will be cleaned up by MyEntityManager
        if (container.getWidth() + 50 < position.x || container.getHeight() + 50 < position.y)
        {
            deadBullet = true;
        }
        if (-50 > position.x || -50 > position.y)
        {
            deadBullet = true;
        }

        position.add(velocity);

        for (int i = 0; i < temp.size(); i++)
        {
            BlockEntity e = ((BlockEntity) temp.get(i));
            //will not hit original block that fired the bullet
            if (!e.getName().equals(originBlock) && e.getBlock().contains(this.getPosition().x, this.getPosition().y))
            {
                //damage here!
                //remove bullet from MyEntityManager
                //TODO: Make damage better!
                deadBullet = true;
                e.takeDamage(this.damage);

                //destroy BlockEntity if below 0 hp
                if (e.getHP() < 0)
                {
                    temp.remove(i);
                }
            }
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        g.drawOval(getPosition().x, getPosition().y, 2, 2);
    }
}
