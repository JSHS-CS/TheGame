/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

/**
 *
 * @author pham266693
 */
public class MousePosition implements MouseListener
{

    public static MousePosition singleton;


    public static MousePosition getMouse()
    {
        if (singleton != null)
        {
            return singleton;
        }
        singleton = new MousePosition();
        return singleton;
    }

    public MousePosition()
    {
    }

    @Override
    public void mouseWheelMoved(int change)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(int button, int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(int button, int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setInput(Input input)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAcceptingInput()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void inputEnded()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void inputStarted()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
