/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.component;

import net.mrfornal.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 */
public class TestMovement extends Component
{
    
    private Vector2f velocity;
    
    public TestMovement(Entity owner)
    {
        super(owner);
    }
    
    @Override
    public void update(GameContainer container, int delta)
    {
        if (velocity == null) return;
        
        getOwner().getPosition().add(velocity);
    }

    @Override
    public void init(GameContainer container)
    {
        float magnitude = (float)(Math.random() * 3);
        velocity = new Vector2f(getOwner().getRotation());
        velocity.normalise().scale(magnitude);
    }
    
}
