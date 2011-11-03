/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.component;

import net.mrfornal.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author sfornal
 */
public abstract class RenderableComponent extends Component
{
    public RenderableComponent(Entity owner)
    {
        super(owner);
    }
     
    public RenderableComponent(Entity owner, String name)
    {
        super(owner, name);
    }
    
    public abstract void render(GameContainer container, Graphics g) throws SlickException;
}
