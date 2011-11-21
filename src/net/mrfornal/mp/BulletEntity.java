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

    private float initSpeed; //TODO: Make unique bullet speeds - NOT IMPLEMENTED
    private int damage; //TODO: Put in a static class that handles bullet types and their assigned damage values (HashMap)
    private Vector2f velocity;

    public BulletEntity()
    {
    }

    public BulletEntity(int dmg, Vector2f v)
    {
        damage = dmg;
        //initSpeed is a 100 at default - TODO: implement initSpeed
        velocity = v;
        Vector2f temp = new Vector2f(2/*initSpeed*/, 0);
        temp.sub(v.getTheta());
        velocity.add(temp);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        ArrayList<BlockEntity> temp = MyEntityManager.getInstance().getBlockEntities();
        ArrayList<BulletEntity> temp2 = MyEntityManager.getInstance().getBulletEntities();

        int index = 0;

        for (int i = 0; i < temp2.size(); i++)
        {
            if (temp2.get(i).equals(this))
            {
                index = i;
            }
        }

        for (int i = 0; i < temp.size(); i++)
        {
            BlockEntity e = ((BlockEntity) temp.get(i));
            if (e.getBlock().contains(this.getPosition().x, this.getPosition().y))
            {
                //damage here!
                //remove bullet from MyEntityManager
                //TODO: Make damage/removal better!
                temp2.remove(index);
                e.takeDamage(this.damage);
                    
                //destroy BlockEntity if below 0 hp
                if(e.getHP()<0)
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
