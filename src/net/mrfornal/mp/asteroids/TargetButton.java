/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author pham266693
 */
public class TargetButton extends MouseOverArea
{

    Targetable currentTarget;

    public TargetButton(GUIContext container, Image image, Shape shape)
    {
        super(container, image, shape);
    }

    @Override
    public void mouseReleased(int button, int mx, int my)
    {
        if (isMouseOver()&&button == 0&&this.currentTarget!=null)
        {
            TargetingGUI.currentTarget = this.currentTarget.getTarget();
        }
    }

    public void update(GameContainer container)
    {
    }

    public Targetable getCurrentTarget()
    {
        return currentTarget;
    }

    public void setCurrentTarget(Targetable currentTarget)
    {
        this.currentTarget = currentTarget;
    }
    
    public void render(GameContainer container, Graphics g, float posX, float posY)
    {
        if ((currentTarget!=null&&TargetingGUI.currentTarget!=null&&currentTarget.equals(TargetingGUI.currentTarget))||isMouseOver())
        {
            g.setColor(Color.yellow);
        }
        if (currentTarget != null)
        {
            g.drawString(currentTarget.getText(), posX, posY);
        }
        g.setColor(Color.white);
    }
}
