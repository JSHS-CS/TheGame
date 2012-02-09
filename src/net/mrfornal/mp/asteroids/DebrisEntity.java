/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp.asteroids;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author pham266693
 */
public class DebrisEntity extends BlockEntity
{

    DebrisEntity(Shape s, String name, float mass, float x, float y, float vX, float vY, int maxHP, Image spr) throws SlickException
    {

        super(s, name, mass, x, y, vX, vY, maxHP, spr);
    }
    //same thing as a regular block, but size decreases with HP

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        //code to remove if HP is equal to or under 0 is in BulletEntity

//        ArrayList<Entity> list = MyEntityManager.getInstance().getEntitiesOfType(getClass());
        ArrayList<BlockEntity> list = MyEntityManager.getInstance().getBlockEntities();
        updateMovement(container, list);

        if (!((int) block.getWidth() / 2 <= (getHP() / 80 + 10) + 5 && (int) block.getWidth() / 2 >= (getHP() / 80 + 10) - 5))
        {
            block = new Circle(block.getCenterX(), block.getCenterY(), getHP() / 80 + 10);
            position.set(block.getX(), block.getY());
            setMass(getHP());
        }
    }
}
