package net.mrfornal.mp;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.mrfornal.TheGame;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author sfornal
 */
public class TopDownGame extends BasicGame
{


    private TDEntityManager manager;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final boolean FULL_SCREEN = false;
    public static boolean debugRender = false;

    
    public TopDownGame(String title)
    {
        super(title);
        manager = TDEntityManager.getInstance();
    }
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
            AppGameContainer game = new AppGameContainer(new TopDownGame("Test"));
            game.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
            game.start();
        } catch (SlickException ex)
        {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        
        
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input i = container.getInput();

        if (i.isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }

        
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        
    }
}
