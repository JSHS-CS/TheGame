/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.lts;

import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
        
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();
        
        if(i.isKeyPressed(Input.KEY_ESCAPE))
        {
            System.exit(0);
        }
        
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        
    }
    
}
