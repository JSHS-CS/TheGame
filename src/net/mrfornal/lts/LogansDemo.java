/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.lts;

import net.mrfornal.game.*;
import org.newdawn.slick.Color;
import net.mrfornal.entity.BasicTestEntity;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author stone205446
 */
public class LogansDemo extends BasicGame
{
    private EntityManager eManager;
    
    public LogansDemo(String title)
    {
        super(title);
        eManager = EntityManager.getInstance();      
    }
    
    @Override
    public void init(GameContainer container) throws SlickException
    {
        
        eManager.addEntity(new Car(new Vector2f(), 0, 0));

        for (Entity e : eManager.getAllEntities())
        {
            e.init(container);
        }        
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();
        for (Entity e : eManager.getAllEntities())
        {
            e.update(container, delta);
        }
        if(i.isKeyPressed(Input.KEY_ESCAPE))
        {
            System.exit(0);
        }
        
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        for (Entity e : eManager.getAllEntities())
        {
            e.render(container, g);
        }
    }
    
}
