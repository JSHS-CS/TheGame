/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.far;

import net.mrfornal.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Color;
import net.mrfornal.entity.BasicTestEntity;
import net.mrfornal.entity.Entity;
import net.mrfornal.entity.EntityManager;
import org.newdawn.slick.BasicGame;



;

/**
 *
 * @author sfornal
 */
class FernandoGame extends BasicGame
{
    private Image sprite;
    private Vector2f spritePos;
    private float spriteScale;

    public FernandoGame(String title)
    {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException
    {
        sprite = new Image("resource/image/space_ship.png");
        spritePos = new Vector2f(container.getWidth() / 2.0f, container.getHeight() / 2.0f);
        spriteScale = 1.0f;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        Input input = container.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE))
        {
            container.exit();
        }
        if (input.isKeyPressed(Input.KEY_SPACE))
        {
            spriteScale += 0.1f * delta;
        }
        if (input.isKeyPressed(Input.KEY_D))
        {
            spritePos.x = spritePos.x + 10;
        }
        if (input.isKeyPressed(Input.KEY_A))
        {
            spritePos.x = spritePos.x - 10;
        }
        if (spritePos.x == 0 && input.isKeyPressed(Input.KEY_A))
        {
            spritePos.x = spritePos.x + 10;
        }
        
               
               
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        sprite.draw(spritePos.x - sprite.getWidth()/2, 500);
        // This code should center the sprite ^^
    }
    
}
