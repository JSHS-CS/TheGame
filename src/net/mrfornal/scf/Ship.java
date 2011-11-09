package net.mrfornal.scf;

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
 * @author sfornal
 */
public class Ship extends Entity
{

    private Image image;
    private Vector2f velocity;
    private Vector2f acceleration;
    private Color color;
    private boolean isAccelerating;
    
    public Ship(Vector2f position, float rotation, float scale, int layer, Color color) throws SlickException
    {
        super();
        image = new Image("resource/image/space_ship.png");
        image.setFilter(Image.FILTER_LINEAR);
        image.rotate(90);
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.layer = layer;
        this.color = color;
        velocity = new Vector2f(rotation).normalise().scale(0.5f);
        acceleration = new Vector2f(rotation).normalise().scale(0.01f);
        
    }

    public void setVelocity(Vector2f velocity)
    {
        this.velocity = velocity;
    }
    
    public Vector2f getVelocity()
    {
        return velocity;
    }
    
    public void setAcceleration(Vector2f acceleration)
    {
        this.acceleration = acceleration;
    }
    
    public Vector2f getAcceleration()
    {
        return acceleration;
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
        
        if (i.isKeyDown(Input.KEY_LEFT)) rotation -= 0.1f * delta;
        
        if (i.isKeyDown(Input.KEY_RIGHT)) rotation += 0.1f * delta;
        
        if (i.isKeyDown(Input.KEY_UP))
        {
            isAccelerating = true;
            acceleration = new Vector2f(rotation);
            acceleration.normalise().scale(0.01f * delta);
        }
        else
        {
            isAccelerating = false;
            acceleration.scale(0);
        }
        
        velocity.add(acceleration);
        
        if (velocity.length() > 3) velocity.normalise().scale(3);
        
        position.add(velocity);
        
        if (position.x > container.getWidth()) position.x = Math.abs(container.getWidth() - position.x);
        if (position.y > container.getHeight()) position.y = Math.abs(container.getHeight() - position.y);
        
        if (position.x < 0) position.x = container.getWidth() - Math.abs(position.x);
        if (position.y < 0) position.y = container.getHeight() - Math.abs(position.y);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        image.setRotation(rotation);
        float x = position.x - image.getWidth();
        float y = position.y - image.getHeight();
        image.draw(x, y, scale, color);
        
        g.setColor(Color.green);
        if (isAccelerating)
        {
            g.fillRoundRect(10, 10, 10, 10, 2);
        }
        else
        {
            g.drawRoundRect(10, 10, 10, 10, 2);
        }
        
        g.drawString(position.toString(), position.x , position.y + image.getHeight() / 2 + 10);
        g.drawString(velocity.toString(), position.x , position.y + image.getHeight() / 2 + 30);
        g.drawString(acceleration.toString(), position.x , position.y + image.getHeight() / 2 + 50);
    }
}
