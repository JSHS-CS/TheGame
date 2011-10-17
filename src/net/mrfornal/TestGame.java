/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 */
class TestGame extends BasicGame
{
    private Image sprite;
    private Vector2f spritePos;

    public TestGame()
    {
        super("TestGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        sprite = new Image("resource/image/space_ship.png");
        spritePos = new Vector2f(container.getWidth() / 2.0f, container.getHeight() / 2.0f);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input input = container.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        sprite.draw(spritePos.x, spritePos.y, 1.0f);
    }
    
}
