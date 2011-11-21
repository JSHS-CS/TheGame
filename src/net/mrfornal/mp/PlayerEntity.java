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
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
public class PlayerEntity extends BlockEntity
{

    public PlayerEntity(Shape s, String name, float mass, float x, float y, float vX, float vY, int maxhp)
    {
        super(s, name, mass, x, y, vX, vY, maxhp);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        ArrayList<Entity> list = MyEntityManager.getInstance().getEntitiesOfType(getClass());

        for (Entity e : list)
        {
            if (!e.equals(this) && ((BlockEntity) e).getMass() > 50)
            {
                float x1 = /*this.getPosition().x + */this.getBlock().getCenterX();
                float y1 = /*this.getPosition().y + */this.getBlock().getCenterY();
                float x2 = /*e.getPosition().x + */((BlockEntity) e).getBlock().getCenterX();
                float y2 = /*e.getPosition().y + */((BlockEntity) e).getBlock().getCenterY();
                float m2 = ((BlockEntity) e).getMass();

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

                if (this.getBlock().intersects(((BlockEntity) e).getBlock()))
                {
                    velocity.set(-velocity.x, -velocity.y);
                    setPosition(getPosition().add(velocity));
                }
            }
        }
        setPosition(getPosition().add(velocity));

        block.setX(getPosition().x);
        block.setY(getPosition().y);


        //prevents from leaving screen
        if (container.getWidth() + 200 < position.x)
        {
            velocity.set(-velocity.x, velocity.y);
        }
        if (container.getHeight() + 200 < position.y)
        {
            velocity.set(velocity.x, -velocity.y);
        }
        if (-200 > position.x)
        {
            velocity.set(-velocity.x, velocity.y);
        }
        if (-200 > position.y)
        {
            velocity.set(velocity.x, -velocity.y);
        }

        System.out.println(this.toString());


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
        g.drawLine(block.getCenterX(),
                block.getCenterY(),
                (float) 200 * velocity.x + block.getCenterX(),
                (float) 200 * velocity.y + block.getCenterY());
    }
}
