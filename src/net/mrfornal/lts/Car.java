package net.mrfornal.lts;

import net.mrfornal.entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author stone205446
 */
public class Car extends Entity
{
    private Image car;
    private Image track;
    private Vector2f velocity;
    private Vector2f acceleration;
    private boolean debugging;
    private float MAX_SPEED = 0.7f;
    float friction = 0.999f;
    
    public Car(Vector2f position, float rotation, int layer) throws SlickException
    {
        super();
        car = new Image("resource/image/ctsv.png");
        track = new Image("resource/image/race-track.png");
        this.position = position;
        this.rotation = rotation;
        this.layer = layer;
        debugging = false;
        velocity = new Vector2f(0,0);
        acceleration = new Vector2f(.0009f,0);      
    }
    
    public void setVelocity(Vector2f velocity)
    {
        this.velocity = velocity;
    }
    
    public void setAcceleration(Vector2f acceleration)
    {
        this.acceleration = acceleration;
    }
    
    public void setFriction(float f)
    {
        friction = f;
    }
        
    public Vector2f getVelocity()
    {
        return velocity;
    }
    
    public Vector2f getAcceleration()
    {
        return acceleration;
    }
    
    public float getFriction()
    {
        return friction;
    }
    
    @Override
    public void init(GameContainer container) throws SlickException
    {
        position.x = container.getWidth() / 2;
        position.y = container.getHeight() / 2;     
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();
        
        acceleration.setTheta(rotation);
        
        //prevent car from going over the MAX_SPEED
        if(velocity.length() < MAX_SPEED)
        {
            if (i.isKeyDown(Input.KEY_LEFT))
            {
                rotation -= 0.1f * delta;
                velocity.scale((0.997f + friction)/2);
                car = new Image("resource/image/ctsv.png");
            }
            if (i.isKeyDown(Input.KEY_RIGHT))
            { 
                rotation += 0.1f * delta;
                velocity.scale((0.997f + friction)/2);
                car = new Image("resource/image/ctsv.png");
            }
            if(i.isKeyDown(Input.KEY_DOWN))
            {
                velocity.scale((0.995f + friction)/2);
                car = new Image("resource/image/ctsv.png");
            }
            
            //add acceleration to the velocity vector to go
            if (i.isKeyDown(Input.KEY_UP))
            {
                velocity.add(acceleration);
                velocity.scale(friction);
                car = new Image("resource/image/ctsv-flame.png");
            }
            
            //friction will slow the car down
            if(!i.isKeyDown(Input.KEY_LEFT) && !i.isKeyDown(Input.KEY_RIGHT) 
                    && !i.isKeyDown(Input.KEY_UP) && !i.isKeyDown(Input.KEY_DOWN))
            {
                velocity.scale(friction);
                car = new Image("resource/image/ctsv.png");
            }   
        }       
        else
        {
            velocity.scale(MAX_SPEED);
        }
        
        position.add(velocity);
        
        
        //code to wrap around screen
        if (position.x > container.getWidth())
        {
            position.x = Math.abs(container.getWidth() - position.x);
        }
        if (position.y > container.getHeight()) 
        {
            position.y = Math.abs(container.getHeight() - position.y);
        }
        if (position.x < 0) 
        {
            position.x = container.getWidth() - Math.abs(position.x);
        }
        if (position.y < 0)
        {
            position.y = container.getHeight() - Math.abs(position.y);
        }
        
        
        //debugging
        if(i.isKeyPressed(Input.KEY_Q))
        {
            if(debugging == false)
                debugging = true;
            else if(debugging == true)
                debugging = false;
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        car.setRotation(rotation);
        float x = position.x - car.getWidth();
        float y = position.y - car.getHeight();
        track.draw(0,0,1);
        car.draw(x, y, 1);
        
        //debugging
        if(debugging == true)
        {
            g.drawString("Position " + position.toString(), 100 , 0);
            g.drawString("Velocity " + velocity.toString(), 100 , 20);
            g.drawString("Acceleration " + acceleration.toString(), 100 , 40);         
        }
    }   
}
