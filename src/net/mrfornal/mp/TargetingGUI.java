/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    //0 other;
    //1 dockable;
    //2 ships;
    //3 all;
    //4 important;
    private ArrayList<MouseOverArea> typeButtons = new ArrayList<MouseOverArea>();
    private BlockEntity currentTarget;
    //objects in targetable range
    private ArrayList<Targetable> targets;
    //absolute position of targetingGUI
    private float posY;
    private float posX;
    private int ticker = 0;
    public static final int UPDATE_INTERVAL = 450;

    public TargetingGUI(GameContainer container) throws SlickException
    {
        posY = container.getHeight() - 175;
        posX = 0;
        //initialize everything
        //
        //in order of other, dockable, ships, all, important
        typeButtons.add(new MouseOverArea(container, new Image("resource/image/tgui_other.png"), new Rectangle(posX + 5, posY, 25, 25)));
        typeButtons.add(new MouseOverArea(container, new Image("resource/image/tgui_dockable.png"), new Rectangle(posX + 30, posY, 25, 25)));
        typeButtons.add(new MouseOverArea(container, new Image("resource/image/tgui_ships.png"), new Rectangle(posX + 55, posY, 25, 25)));
        typeButtons.add(new MouseOverArea(container, new Image("resource/image/tgui_all.png"), new Rectangle(posX + 80, posY, 25, 25)));
        typeButtons.add(new MouseOverArea(container, new Image("resource/image/tgui_important.png"), new Rectangle(posX + 105, posY, 25, 25)));

        typeButtons.get(0).setMouseOverImage(new Image("resource/image/tgui_other_h.png"));
        typeButtons.get(1).setMouseOverImage(new Image("resource/image/tgui_dockable_h.png"));
        typeButtons.get(2).setMouseOverImage(new Image("resource/image/tgui_ships_h.png"));
        typeButtons.get(3).setMouseOverImage(new Image("resource/image/tgui_all_h.png"));
        typeButtons.get(4).setMouseOverImage(new Image("resource/image/tgui_important_h.png"));

        targets = new ArrayList<Targetable>();
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
    ArrayList<BlockEntity> list;

    public void update(GameContainer container, int delta) throws SlickException
    {
        list = MyEntityManager.getInstance().getBlockEntities();
        //ticker runs every 30 game ticks (or whatever update interval is.)
        if (ticker == UPDATE_INTERVAL)
        {
            for (BlockEntity block : list)
            {
                boolean alreadyExists = false;
                for (Targetable target : targets)
                {
                    if (target.getTarget().equals(block) || block.equals(MyEntityManager.getInstance().getPlayer()))
                    {
                        alreadyExists = true;
                    }
                }
                if (!alreadyExists)
                {
                    targets.add(new Targetable(container, block, (int) posX, (int) posY));
                }
            }

            Collections.sort(targets, new DistanceSorter());
            ticker = 0;
        } else
        {
            ticker++;
        }
    }

    public void render(GameContainer container, Graphics g) throws SlickException
    {
        for (MouseOverArea button : typeButtons)
        {
            button.render(container, g);
        }
        //only draws the first several objects in the array
        //in case there are less than 6 objects
        if (targets.size() > 6)
        {
            for (int i = 0; i < 6; i++)
            {
                targets.get(i).render(container, g, posX, posY + 15 * i);
            }
        } else
        {
            for (int i = 0; i < targets.size(); i++)
            {
                targets.get(i).render(container, g, posX, posY + 15 * i);
            }
        }

    }

    public void debugRender()
    {
    }
}
