/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.game;

import net.mrfornal.entity.BasicTestEntity;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author sfornal
 */
public class BasicEntityGame extends BasicGame
{
    private EntityManager manager;
    
    public BasicEntityGame(String title)
    {
        super(title);
        manager = EntityManager.getInstance();
    }
    
    @Override
    public void init(GameContainer container) throws SlickException
    {
        for (int i = 0; i < 100; i++)
        {
            float rot = (float)(Math.random() * 360);
            float x = (float)(Math.random() * container.getWidth());
            float y = (float)(Math.random() * container.getHeight());
            
            BasicTestEntity e = new BasicTestEntity(x, y, rot);
            manager.addEntity(e);
        }
        
        for (Entity e : manager.getAllEntities())
        {
            e.init(container);
        }
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();
        
        if (i.isKeyPressed(Input.KEY_ESCAPE)) container.exit();
        
        for (Entity e : manager.getAllEntities())
        {
            e.update(container, delta);
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        for (Entity e : manager.getAllEntities())
        {
            e.render(container, g);
        }
    }
}
