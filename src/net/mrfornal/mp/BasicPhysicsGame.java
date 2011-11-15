/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.mrfornal.TheGame;
import net.mrfornal.game.*;
import org.newdawn.slick.Color;
import net.mrfornal.entity.BasicTestEntity;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author sfornal
 */
public class BasicPhysicsGame extends BasicGame
{

    private EntityManager manager;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final boolean FULL_SCREEN = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        final String sep = System.getProperty("file.separator");
        System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + sep + "resource" + sep + "native");
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));

        try
        {
            AppGameContainer game = new AppGameContainer(new BasicPhysicsGame("Test"));
            game.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
            game.start();
        } catch (SlickException ex)
        {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BasicPhysicsGame(String title)
    {
        super(title);
        manager = EntityManager.getInstance();
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        container.setMinimumLogicUpdateInterval(25);
        
        BlockEntity b = new BlockEntity(new Rectangle(0, 0,40,40), "TestBlock2", 1000, 200, 400, +.1f, 0);
        BlockEntity c = new BlockEntity(new Rectangle(0, 0, 40, 40), "TestBlock3", 1000, 400, 200, -.1f, 0);
        BlockEntity a = new BlockEntity(new Rectangle(0, 0, 40, 40), "TestBlock3", 1000, 300, 300, 0, 0);
        manager.addEntity(b);
        manager.addEntity(c);
        manager.addEntity(a);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();

        if (i.isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }

        for (Entity e : manager.getAllEntities())
        {
            e.update(container, delta);
        }

        if (i.isKeyPressed(Input.KEY_1))
        {
            container.setMinimumLogicUpdateInterval(25);
        }
        if (i.isKeyPressed(Input.KEY_2))
        {
            container.setMinimumLogicUpdateInterval(15);
        }
        if (i.isKeyPressed(Input.KEY_3))
        {
            container.setMinimumLogicUpdateInterval(10);
        }
        if (i.isKeyPressed(Input.KEY_4))
        {
            container.setMinimumLogicUpdateInterval(5);
        }
        if (i.isKeyPressed(Input.KEY_5))
        {
            container.setMinimumLogicUpdateInterval(3);
        }
        if (i.isKeyPressed(Input.KEY_6))
        {
            container.setMinimumLogicUpdateInterval(1);
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
