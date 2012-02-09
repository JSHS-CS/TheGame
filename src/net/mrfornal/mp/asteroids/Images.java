/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author pham266693
 */
public class Images
{

    public static Images singleton;
    public HashMap imgMap = new HashMap<String,Image>();

    public static Images getImages()
    {
        if (singleton != null)
        {
            return singleton;
        }
        singleton = new Images();
        return singleton;
    }
    
    public void loadImages() throws SlickException{
    imgMap.put("ship1-0", new Image("resource/image/mp1.png"));
    imgMap.put("ship1-1", new Image("resource/image/mp1a.png"));
    imgMap.put("yellowsquare", new Image("resource/image/yellowsquare.png"));
    
    }

    public Image getImage(String name)
    {
        return (Image)(imgMap.get(name));
    }
    
    
}
