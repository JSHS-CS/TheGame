package net.mrfornal.lts;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author stone205446
 */
public class GameRunner
{

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
            AppGameContainer game = new AppGameContainer(new LogansDemo("Logan's Demo"));
            game.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
            game.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(GameRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}