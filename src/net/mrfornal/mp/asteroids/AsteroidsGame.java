/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import java.awt.event.MouseListener;
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
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 * @author pham266693
 */
public class AsteroidsGame extends BasicGame
{

    public static final int BOUNDARY = 5000; //extra boundary on top of screen size
    private MyEntityManager manager;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final boolean FULL_SCREEN = false;
    public static boolean debugRender = false;
    private TargetingGUI targetGUI;
    private static final int MAX_PARTICLES = 1000;

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
        targetGUI = new TargetingGUI(container);

        container.getInput().addMouseListener(new MouseZoom());
        container.setMinimumLogicUpdateInterval(25);

        Image img1 = new Image("resource/image/mp1.png");
        Image img2 = new Image("resource/image/mp1a.png");
        Image img3 = new Image("resource/image/yellowsquare.png");

        //BlockEntity b = new BlockEntity(new Circle(0, 0, 150), "TestBlock2", 7000, 180, 130, +.0f, 0, 20000, null);
        //BlockEntity c = new BlockEntity(new Circle(0, 0, 25), "TestBlock2", 1000, 100, 100, +.0f, 0, 500);
        PlayerEntity a = new PlayerEntity(new Circle(0, 0, 50), "TestBlock1", 100, 0, 0, -.22f, .05f, 2000, img1, img2);
        //BlockEntity c = new BlockEntity(new Circle(0, 0, 2), "TestBlock3", 3, 440, 240, +.23f, 0);
        //manager.addBlockEntity(c);

        for (int i = 1; i < 80; i += 10)
        {
            manager.addBlockEntity(new DebrisEntity(new Circle(0, 0, 10 * i), "TestBlock" + (i + 20) / 10, 500 * i, 40 * i - 400, 15 * i - 400, +.0f, 0, 500 * i, null));
        }
        for (int i = 1; i < 80; i += 10)
        {
            manager.addBlockEntity(new DebrisEntity(new Circle(0, 0, 10 * i), "TestBlock" + (i + 20) / 10, 500 * i, 40 * i - 700, 15 * i - 800, +.0f, 0, 500 * i, null));
        }

        manager.addBlockEntity(a);
        manager.setPlayer(a);
//        manager.addBlockEntity(b);


        for (int i = 0; i < MAX_PARTICLES; i++)
        {
            manager.addParticle(new Particle());

        }

        Images.getImages().loadImages();

        ParticleSystem pS = new ParticleSystem(0,0);
        ParticleEmitter pE = new ParticleEmitter(0, 0, 1.0f, .5f, Images.getImages().getImage("yellowsquare"), 5);
        pE.setIsActive(true);
        ParticleEmitter pE2 = new ParticleEmitter(10, 50, 1.0f, .5f, Images.getImages().getImage("yellowsquare"), 5);
        pE.setIsActive(true);
        ParticleEmitter pE3 = new ParticleEmitter(50, 10, 1.0f, .5f, Images.getImages().getImage("yellowsquare"), 5);
        pE.setIsActive(true);
        pS.addEmitter(pE);
        pS.addEmitter(pE2);
        pS.addEmitter(pE3);
        manager.addParticleSystem(pS);

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        for (ParticleSystem pS : manager.getAllParticleSystems())
        {
            pS.update(container);
        }
        for (Particle p : manager.getAllParticles())
        {
            p.update();
        }

        for (Entity e : manager.getAllEntities())
        {
            e.update(container, delta);
        }

        Input i = container.getInput();

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
        if (i.isKeyPressed(Input.KEY_0))
        {
            debugRender = !debugRender;
        }

        MyEntityManager.getInstance().checkBoundaries(container);

        targetGUI.update(container, delta);
    }
    public static float scale = 1;

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {


        Input i = container.getInput();

        Entity center = null;
        for (Entity e : manager.getBlockEntities())
        {
            if (e.getName().equals("TestBlock1"))
            {
                center = e;
            }
        }
        if (center != null)
        {
            g.translate(container.getWidth() / 2 - ((BlockEntity) center).getBlock().getCenterX() * scale, container.getHeight() / 2 - ((BlockEntity) center).getBlock().getCenterY() * scale);
        }

        g.scale(scale, scale);


        int c = 0;
        for (Particle p : manager.getAllParticles())
        {
            p.render(container, g);
            if (p.isUsed())
            {
                c++;
            }
        }
        //System.out.println(c);


        for (Entity e : manager.getAllEntities())
        {
            if (e.getName().equals("TestBlock1"))
            {
                center = e;
            }
            //TODO: Make dynamic rendering only render things on screen
            //if (e.getPosition().x > -BOUNDARY && e.getPosition().x < container.getWidth() + BOUNDARY && e.getPosition().y > -BOUNDARY && e.getPosition().y < container.getHeight() + BOUNDARY)
            {
                e.render(container, g);
            }
        }
        g.drawRect(-BOUNDARY, -BOUNDARY, container.getWidth() + BOUNDARY * 2, container.getHeight() + BOUNDARY * 2);


        targetGUI.renderCrosshairs(container, g);
        //draw GUI
        //unscale the zoom so GUI isn't affected
        g.scale(1 / scale, 1 / scale);
        if (center != null)
        {
            g.translate(-container.getWidth() / 2 - -((BlockEntity) center).getBlock().getCenterX() * scale, -container.getHeight() / 2 - -((BlockEntity) center).getBlock().getCenterY() * scale);
        }
        targetGUI.render(container, g);


    }
}
