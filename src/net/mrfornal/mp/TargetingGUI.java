/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author pham266693
 */
public class TargetingGUI
{
    
    //Type filters
    MouseOverArea other;
    MouseOverArea dockable;
    MouseOverArea ships;
    MouseOverArea all;
    //End Type filters
    
    private BlockEntity currentTarget;
    //objects in targetable range
    ArrayList<Targetable> target;
    
    //absolute position of targetingGUI
    float posY;
    float posX;
    
    public TargetingGUI(GameContainer container) throws SlickException
    {
        float posY = container.getHeight()-125;
        float poxX = 0;
        //initialize everything
        other = new MouseOverArea(container, new Image("resource/image/tgui_other.png"), new Rectangle(posX+5, posY, 25, 25));
        dockable = new MouseOverArea(container, new Image("resource/image/tgui_dockable.png"), new Rectangle(posX+30, posY, 25, 25));
        ships = new MouseOverArea(container, new Image("resource/image/tgui_ships.png"), new Rectangle(posX+55, posY, 25, 25));
        all = new MouseOverArea(container, new Image("resource/image/tgui_all.png"), new Rectangle(posX+80, posY, 25, 25));
        
    }
    
    //concept for bottom left targeting GUI
    /* 
     * {Other} {Dockables} {Ships} {All} {Important}
     * =============================================
     * Type |          ObjectName         | Distance
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    
    public void update(GameContainer container, int delta) throws SlickException
    {
        
        
        
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        other.render(container, g);
        dockable.render(container, g);
        ships.render(container, g);
        all.render(container, g);
        
    }
    
    public void debugRender()
    {
        
    }
}
