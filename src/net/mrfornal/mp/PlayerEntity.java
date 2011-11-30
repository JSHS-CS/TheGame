/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import net.mrfornal.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author pham266693
 */
public class PlayerEntity extends BlockEntity
{

    //governs what direction input will cause to accelerate
    private double theta;
    private Vector2f direction;

    public PlayerEntity(Shape s, String name, float mass, float x, float y, float vX, float vY, int maxhp)
    {
        super(s, name, mass, x, y, vX, vY, maxhp);
        direction = new Vector2f(.003f, 0); //default acceleration
        direction.setTheta(theta);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();
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
                    Vector2f positionCalc = new Vector2f();
                    positionCalc.set(position);
                    positionCalc.add(e.getPosition());
                    double theta2 = positionCalc.getTheta();
                    double theta1 = velocity.getTheta();
                    velocity.setTheta(theta1+2*theta2);
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

        direction.setTheta(theta);
        if (i.isKeyDown(Input.KEY_W))
        {
            velocity.add(direction);
        }
        if (i.isKeyDown(Input.KEY_A))
        {
            theta -= 1;
        }
        if (i.isKeyDown(Input.KEY_S))
        {
            velocity.add(direction.negate());
        }
        if (i.isKeyDown(Input.KEY_D))
        {
            theta += 1;
        }
        if (i.isKeyPressed(Input.KEY_LCONTROL))
        {
            Vector2f pos=new Vector2f(block.getCenterX(),block.getCenterY());
            
            
            MyEntityManager.getInstance().addBulletEntity(new BulletEntity(10, velocity, pos,theta,name));
        }
        System.out.println(direction.toString());
        System.out.println(theta);


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

        //draws a line that represents velocity vector
//        g.drawLine(block.getCenterX(),
//                block.getCenterY(),
//                (float) 200 * velocity.x + block.getCenterX(),
//                (float) 200 * velocity.y + block.getCenterY());
        //draws a line that represents player direction
        g.drawLine(block.getCenterX(),
                block.getCenterY(),
                (float) 30000 * direction.x + block.getCenterX(),
                (float) 30000 * direction.y + block.getCenterY());
    }
}
