/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.component;

import net.mrfornal.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 */
public class TestRender extends RenderableComponent
{
    private Shape circle;
    private static final float RADIUS = 30.0f;
    private Line line;
    
    public TestRender(Entity owner)
    {
        super(owner);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        circle = new Ellipse(getOwner().getPosition().x, getOwner().getPosition().y, RADIUS, RADIUS);
        float x2 = (float) (getOwner().getPosition().x + (RADIUS * Math.cos(Math.toRadians(getOwner().getRotation()))));
        float y2 = (float) (getOwner().getPosition().y + (RADIUS * Math.sin(Math.toRadians(getOwner().getRotation()))));
        Vector2f end = new Vector2f(x2, y2);
        line = new Line(getOwner().getPosition(), end);
        
        if (circle != null)
            g.draw(circle);
        
        if (line != null)
            g.draw(line);
    }

    @Override
    public void update(GameContainer container, int delta)
    {
             
    }

    @Override
    public void init(GameContainer container)
    {
        
    }
    
}
