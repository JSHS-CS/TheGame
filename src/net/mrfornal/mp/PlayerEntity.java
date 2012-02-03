/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

/**
 *
 * @author pham266693
 */
public class PlayerEntity extends BlockEntity
{
    //governs what direction input will cause to accelerate

    private double theta;
    private Vector2f engineAcceleration;
    private Image accelerationSprite;
    int weaponCooldownMax = 20;
    int cooldown = 0;
    private Vector2f engineParticle1;
    private Vector2f engineParticle2;
    Image image;
    ParticleSystem system;

    public PlayerEntity(Shape s, String name, float mass, float x, float y, float vX, float vY, int maxhp, Image spr, Image aSpr) throws SlickException
    {
        super(s, name, mass, x, y, vX, vY, maxhp, spr);
        engineAcceleration = new Vector2f(.05f, 0); //default acceleration
        engineAcceleration.setTheta(theta);
        accelerationSprite = aSpr;
        //where to emit particles relative to center of block
        engineParticle1 = new Vector2f(-30f, 10f);
        engineParticle2 = new Vector2f(-30f, -10f);

        image = new Image("resource/image/yellowsquare.png", true);
        system = new ParticleSystem(image);
        system.setBlendingMode(1);
//        
    }

    @Override
    public void addToManager()
    {
        MyEntityManager.getInstance().addBlockEntity(this);
    }

    //fires current equipped weapon
    private void fireWeapon() throws SlickException
    {
        Vector2f pos = new Vector2f(block.getCenterX(), block.getCenterY());
        MyEntityManager.getInstance().addBulletEntity(new RocketEntity(3.0f, 2000, velocity, pos, theta, name, block.getWidth() / 2, .07f, new Image("resource/image/mp_missile.png"), TargetingGUI.currentTarget));
    }

    //======================================================================
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        system.update(delta);
//        ArrayList<Entity> list = MyEntityManager.getInstance().getEntitiesOfType(getClass());
        ArrayList<BlockEntity> list = MyEntityManager.getInstance().getBlockEntities();
        updateMovement(container, list);
        updatePlayerInput(container);
    }

    //======================================================================
    @Override
    public void updateMovement(GameContainer container, ArrayList<BlockEntity> list)
    {
        //prevents from leaving screen
        //moved to MyEntityManager
        //edgeCollide(container);
        for (BlockEntity e : list)
        {
//            if (!e.equals(this) && e.getMass() > 50)
//            {
//                float x1 = /*this.getPosition().x + */ this.getBlock().getCenterX(); //turns out getCenter gives you the absolute position
//                float y1 = /*this.getPosition().y + */ this.getBlock().getCenterY();
//                float x2 = /*e.getPosition().x + */ e.getBlock().getCenterX();
//                float y2 = /*e.getPosition().y + */ e.getBlock().getCenterY();
//                float m2 = e.getMass();
//                //old acceleration - x and y - messed up when both objects were near each other. Needed more refinement.
////                float accX =  ugc * m2 / (x2 - x1);
////                float accY =  ugc * m2 / (y2 - y1);
//                float acc = (float) (ugc * m2 / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
//                double theta = Math.atan(Math.abs((y2 - y1) / (x2 - x1)));
//                float accX = Math.abs(acc * (float) Math.cos(theta));
//                float accY = Math.abs(acc * (float) Math.sin(theta));
//                //if the other object is to the left of this object, move negative x
//                if (x2 < x1)
//                {
//                    accX *= -1;
//                }
//                //if the other object is above this object, move negative y?
//                if (y2 < y1)
//                {
//                    accY *= -1;
//                }
//                acceleration.set(new Vector2f(accX, accY));
//                //System.out.println(acceleration.x + " " + acceleration.y);
//                velocity.add(acceleration);
//                //accelerate based on position of other blocks
//                //Collision with another block
//            }
            if (!e.equals(this))
            {
                collide(e);
            }
        }
        setPosition(getPosition().add(velocity));
        block.setX(getPosition().x);
        block.setY(getPosition().y);
    }

    public void applyTheta(double theta)
    {

        engineAcceleration.setTheta(theta);
        sprite.setRotation((float) theta);
        accelerationSprite.setRotation((float) theta);
        engineParticle1.setTheta(theta);
        engineParticle2.setTheta(theta);
    }

    public void updatePlayerInput(GameContainer container) throws SlickException
    {
        //Player Input
        Input i = container.getInput();
        applyTheta(theta);

        if (i.isKeyDown(Input.KEY_W))
        {
            velocity.add(engineAcceleration);
        }
        if (i.isKeyDown(Input.KEY_A))
        {
            theta -= 2.5;
        }
        if (i.isKeyDown(Input.KEY_S))
        {
            velocity.add(engineAcceleration.negate());
        }
        if (i.isKeyDown(Input.KEY_D))
        {
            theta += 2.5;
        }
        if (i.isKeyPressed(Input.KEY_LCONTROL))
        {
            fireWeapon();
        }
        if (i.isKeyDown(Input.KEY_Z) && i.isKeyDown(Input.KEY_LCONTROL))
        {
            cooldown++;
            if (cooldown == weaponCooldownMax)
            {
                fireWeapon();
                cooldown = 0;
            }
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
        
        //TODO: build custom particle system
        g.draw(block);

        if (container.getInput().isKeyDown(Input.KEY_W))
        {
            g.drawImage(accelerationSprite, position.x, position.y);
            if (system.getEmitterCount() == 0)
            {
                //system.addEmitter(new EngineEmitter((int) (engineParticle1.x), (int) (engineParticle1.y), .1f));
                //system.addEmitter(new EngineEmitter((int) (engineParticle2).x, (int) (engineParticle2.y), .1f));

            } else
            {
            }

        } else
        {
            g.drawImage(sprite, position.x, position.y);

        }
        system.render(getCenterPosition().x,getCenterPosition().y);

        if (AsteroidsGame.debugRender)
        {
            g.drawString("" + hp, getPosition().x, getPosition().y);
        }

        //draws a line that represents velocity vector
//        g.drawLine(block.getCenterX(),
//                block.getCenterY(),
//                (float) 200 * velocity.x + block.getCenterX(),
//                (float) 200 * velocity.y + block.getCenterY());

        //draws a line that represents player direction
//        g.drawLine(block.getCenterX(),
//                block.getCenterY(),
//                (float) 30000 * direction.x + block.getCenterX(),
//                (float) 30000 * direction.y + block.getCenterY());
    }
}
