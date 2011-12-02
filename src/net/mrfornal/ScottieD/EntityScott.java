package net.mrfornal.ScottieD;

import net.mrfornal.entity.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 */
public class EntityScott extends Entity
{
    private static final int LAYER = 1;
    private static final float RADIUS = 15;
    private Vector2f velocity;
    private Color color;

    /**
     * Creates a basic entity with the given values
     * @param xPos
     * @param yPos
     * @param rotation 
     */
    public EntityScott(float xPos, float yPos, float rotation, Color col)
    {
        super();
        setPosition(new Vector2f(xPos, yPos));
        setLayer(LAYER);
        setRotation(rotation);
        velocity = new Vector2f(0);
        color = col;
    }

    /**
     * Updates the entity over time
     * @param container
     * @param delta
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        velocity = new Vector2f(getRotation());
        position.add(velocity);
        
        if (position.x > container.getWidth()) position.x -= container.getWidth();
        if (position.y > container.getHeight()) position.y -= container.getHeight();
        
        if (position.x < 0) position.x += container.getWidth();
        if (position.y < 0) position.y += container.getHeight();

    }

    /**
     * Initializes the entity
     * @param container
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer container) throws SlickException
    {
        velocity = new Vector2f(0);
        //velocity.normalise().scale(((float)(Math.random()*.1)));
    }

    /**
     * Paints the entity on the screen
     * @param container
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        g.setColor(color);
        g.drawOval(position.x - RADIUS, position.y - RADIUS, RADIUS * 2, RADIUS * 2);
        g.drawString(name, position.x, position.y + 100);
        g.drawString("TESTIFICATE", position.x, position.y + 115);
        g.draw(new Line(position, position.copy().add(velocity.copy().scale(RADIUS))));
    }
}
