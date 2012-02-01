/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.newdawn.slick.Color;
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
    public static BlockEntity currentTarget;
    //objects in targetable range
    private ArrayList<Targetable> targets;
    private ArrayList<TargetButton> buttons;
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
        buttons = new ArrayList<TargetButton>();

        for (int i = 0; i < 10; i++)
        {
            buttons.add(new TargetButton(container, new Image("resource/image/tgui_empty.png"), new Rectangle(posX, (int) (int) (posY + (15 * i)) + 25, 100, 15)));
        }
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
        for (Targetable target : targets)
        {
            target.update(container);
        }

        list = MyEntityManager.getInstance().getBlockEntities();
        //ticker runs every 30 game ticks (or whatever update interval is.)
        if (ticker == UPDATE_INTERVAL)
        {
            //gets targets to put in targetable
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
            //removes dead targetables
            for (int i = 0; i < targets.size(); i++)
            {
                if (targets.get(i).getTarget().getHP() <= 0)
                {
                    targets.remove(i);
                currentTarget = null;
                }
            }



            Collections.sort(targets, new DistanceSorter());

            for (int i = 0; i < buttons.size() && i < targets.size(); i++)
            {
                buttons.get(i).setCurrentTarget(targets.get(i)); //assigns closest values to top of targeting screen
            }

            ticker = 0;
        } else
        {
            ticker++;
        }
        for (TargetButton b : buttons)
        {
            b.update(container);
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
        for (int i = 0; i < buttons.size(); i++)
        {
            buttons.get(i).render(container, g);
            buttons.get(i).render(container, g, posX, posY + 25 + 15 * i);
        }


    }

    public void renderCrosshairs(GameContainer container, Graphics g) throws SlickException
    {
        if (currentTarget != null && currentTarget.getHP() > 0)
        {

            g.setColor(Color.red);
            g.drawRect(currentTarget.getBlock().getMinX(), currentTarget.getBlock().getMinY(), currentTarget.getBlock().getWidth(), currentTarget.getBlock().getHeight());
            g.drawLine(currentTarget.getBlock().getCenterX() + currentTarget.getBlock().getWidth(), currentTarget.getBlock().getCenterY(), currentTarget.getBlock().getCenterX() - currentTarget.getBlock().getWidth(), currentTarget.getBlock().getCenterY());
            g.drawLine(currentTarget.getBlock().getCenterX(), currentTarget.getBlock().getCenterY() + currentTarget.getBlock().getHeight(), currentTarget.getBlock().getCenterX(), currentTarget.getBlock().getCenterY() - currentTarget.getBlock().getHeight());
            g.setColor(Color.white);
        }
        //crosshairs
    }

    public void debugRender()
    {
    }
}
