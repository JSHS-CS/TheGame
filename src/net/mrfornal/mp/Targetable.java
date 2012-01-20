/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.text.Format;
import java.util.Formatter;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;

/**
 *
 * @author pham266693
 */
public class Targetable
{

            BlockEntity player = MyEntityManager.getInstance().getPlayer();
    protected BlockEntity target;
    protected MouseOverArea button;
    protected String text;
    protected int index; //where to draw the button - order is based on distance
    private int ticker = 0;
    public static final int UPDATE_INTERVAL = 450;
    

    public Targetable(GameContainer container, BlockEntity t, int x, int y) throws SlickException
    {
        text = "";
        target = t;
        button = new MouseOverArea(container, new Image("resource/image/tgui_empty.png"), new Rectangle(x, y, 100, 20));

    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public BlockEntity getTarget()
    {
        return target;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void render(GameContainer container, Graphics g, float posX, float posY)
    {
        
        //should be in update, but this saves space
        if (ticker == UPDATE_INTERVAL)
        {
            Vector2f center = new Vector2f(target.getBlock().getCenterX(), target.getBlock().getCenterY());
            Vector2f otherCenter = new Vector2f(player.getBlock().getCenterX(), player.getBlock().getCenterY());
            int length = (int)(center.sub(otherCenter).length()-player.getBlock().getWidth()/2-target.getBlock().getWidth()/2);
            length/=10;
        //10 pixels = 1 meter, perhaps?
            text = target.getName() + " | " + length;
            ticker = 0;
        }else
            ticker++;

        if (button.isMouseOver())
        {
            g.setColor(Color.yellow);
        }
        g.drawString(text, posX, posY);
        g.setColor(Color.white);
    }
}
