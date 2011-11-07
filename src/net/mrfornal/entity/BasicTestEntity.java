/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.entity;

import net.mrfornal.component.TestRender;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author sfornal
 */
public class BasicTestEntity extends Entity
{
    public BasicTestEntity(float xPos, float yPos, float rotation)
    {
        super();
        setPosition(new Vector2f(xPos, yPos));
        setRotation(rotation);
        addComponent(new TestRender(this));
    }
}
