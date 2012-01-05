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
public class MouseZoom implements MouseListener
{

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
    }

    @Override
    public void mousePressed(int button, int x, int y)
    {
    }

    @Override
    public void mouseReleased(int button, int x, int y)
    {
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy)
    {
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy)
    {
    }

    @Override
    public void setInput(Input input)
    {
    }

    @Override
    public boolean isAcceptingInput()
    {
        return true;
    }

    @Override
    public void inputEnded()
    {
    }

    @Override
    public void inputStarted()
    {
    }

    @Override
    public void mouseWheelMoved(int change)
    {
        AsteroidsGame.scale += (float)change / 5000;
        if(AsteroidsGame.scale<=0.01f)
            AsteroidsGame.scale=.01f;
    }
}