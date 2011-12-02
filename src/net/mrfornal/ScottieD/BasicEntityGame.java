/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.ScottieD;

import net.mrfornal.game.*;
import org.newdawn.slick.Color;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Scott Davis
 */
public class BasicEntityGame extends BasicGame
{

    private EntityManager manager;
    private EntityScott k;
    public BasicEntityGame(String title)
    {
        super(title);
        manager = EntityManager.getInstance();
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        
        
            float rot = 90;
            float x = (float) (Math.random() * container.getWidth());
            float y = (float) (Math.random() * container.getHeight());
            Color col = Color.white;

            k = new EntityScott(x, y, rot, col);
            manager.addEntity(k);
        

        for (Entity e : manager.getAllEntities())
        {
            e.init(container);
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();
        container.setMinimumLogicUpdateInterval(5);

        if (i.isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }
        if(i.isKeyDown(Input.KEY_RIGHT))
        {
            k.setRotation((k.getRotation()+(float)(.5)));
        }
        if(i.isKeyDown(Input.KEY_LEFT))
        {
            k.setRotation((k.getRotation()-(float)(.5)));
        }
        
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
