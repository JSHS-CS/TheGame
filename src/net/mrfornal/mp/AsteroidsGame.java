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
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author sfornal
 */
public class AsteroidsGame extends BasicGame
{

    private MyEntityManager manager;
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
            AppGameContainer game = new AppGameContainer(new AsteroidsGame("Test"));
            game.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
            game.start();
        } catch (SlickException ex)
        {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AsteroidsGame(String title)
    {
        super(title);
        manager = MyEntityManager.getInstance();
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        container.setMinimumLogicUpdateInterval(25);

        BlockEntity b = new BlockEntity(new Circle(0, 0, 35), "TestBlock2", 1500, 380, 330, +.0f, 0, 1000);
        //BlockEntity c = new BlockEntity(new Circle(0, 0, 25), "TestBlock2", 1000, 100, 100, +.0f, 0, 500);
        PlayerEntity a = new PlayerEntity(new Circle(0, 0, 10), "TestBlock1", 40, 400, 180, -.22f, .05f, 200);
        //BlockEntity c = new BlockEntity(new Circle(0, 0, 2), "TestBlock3", 3, 440, 240, +.23f, 0);
        manager.addBlockEntity(a);
        manager.addBlockEntity(b);
        //manager.addBlockEntity(c);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        for (Entity e : manager.getAllEntities())
        {
            e.update(container, delta);
        }

        Input i = container.getInput();
        /*
        BlockEntity b = (BlockEntity) manager.getEntity("TestBlock2");
        
        if (i.isKeyDown(Input.KEY_UP))
        {
        b.getPosition().set(b.getPosition().x,b.getPosition().y-.1f);
        }
        if (i.isKeyDown(Input.KEY_LEFT))
        {
        b.getPosition().set(b.getPosition().x-.1f,b.getPosition().y);
        }
        if (i.isKeyDown(Input.KEY_RIGHT))
        {
        b.getPosition().set(b.getPosition().x+.1f,b.getPosition().y);
        }
        if (i.isKeyDown(Input.KEY_DOWN))
        {
        b.getPosition().set(b.getPosition().x,b.getPosition().y+.1f);
        }
         */

        if (i.isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
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
