/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.mrfornal.TheGame;
import net.mrfornal.entity.Entity;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

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
        
        Image img1 =new Image("resource/image/mp1.png");
        Image img2 =new Image("resource/image/mp1a.png");
        

        BlockEntity b = new BlockEntity(new Circle(0, 0, 150), "TestBlock2", 700, 180, 130, +.0f, 0, 1000,null);
        //BlockEntity c = new BlockEntity(new Circle(0, 0, 25), "TestBlock2", 1000, 100, 100, +.0f, 0, 500);
        PlayerEntity a = new PlayerEntity(new Circle(0, 0, 50), "TestBlock1", 40, 0, 0, -.22f, .05f, 200,img1,img2);
        //BlockEntity c = new BlockEntity(new Circle(0, 0, 2), "TestBlock3", 3, 440, 240, +.23f, 0);
        //manager.addBlockEntity(c);
        manager.addBlockEntity(a);
        manager.addBlockEntity(b);
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
        Entity center = null;
        for (Entity e : manager.getBlockEntities())
        {
            if (e.getName().equals("TestBlock1"))
            {
                center = e;
            }
        }
        g.translate(container.getWidth()/2-((BlockEntity) center).getBlock().getCenterX(), container.getHeight()/2-((BlockEntity) center).getBlock().getCenterY());
        for (Entity e : manager.getAllEntities())
        {
            if (e.getPosition().x > -50 && e.getPosition().x < container.getWidth() + 50 && e.getPosition().y > -50 && e.getPosition().y < container.getHeight() + 50)
            {
                e.render(container, g);
            }
            if (e.getName().equals("TestBlock1"))
            {
                center = e;
            }
        }
        g.drawRect(-50, -50, container.getWidth()+100, container.getHeight()+100);
 
    }
}
