///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.mrfornal.entity;
//
///**
// *
// * @author mitch314032
// */
//
//public class PlayerEntity extends Entity
//{
//    
//    /**
//     * Default constructor assigns a consecutively numbered id value
//     * as the Entity's name
//     * */
//    public PlayerEntity()
//    {
//        this(null);
//    }
//    
//    
//    public PlayerEntity(String name)
//    {
//        super(name);
//    }
//    
//    /**
//     * Get the entity's name
//     * @return the name
//     */
//    public String getName()
//    {
//        super.getName();
//    }
//    
//    /**
//     * Get the Vector2f postion
//     *
//     * @return the value of position
//     */
//    public Vector2f getPosition()
//    {
//        super.getPosition();
//    }
//    
//    /**
//     * Get the value of scale
//     *
//     * @return the scale
//     */
//    public float getScale()
//    {
//        super.getScale();
//    }
//
//    /**
//     * Get the rotation
//     *
//     * @return the rotation
//     */
//    public float getRotation()
//    {
//        super.getRotation();
//    }
//
//    /**
//     * Get the z-index for rendering
//     *
//     * @return the layer
//     */
//    public int getLayer()
//    {
//        super.getLayer();
//    }
//
//    /**
//     * Sets the entity's name
//     * @param name The name of the entity
//     */
//    public void setName(String name)
//    {
//        super.setName(name);
//    }
//
//    /**
//     * Set the position Vector2f
//     *
//     * @param pos new value of position
//     */
//    public void setPosition(Vector2f pos)
//    {
//        super.setPosition(pos);
//    }
//
//    /**
//     * Set the scale
//     *
//     * @param scale new value of scale
//     */
//    public void setScale(float sc)
//    {
//        super.setScale(sc);
//    }
//
//    /**
//     * Set the rotation
//     *
//     * @param rotation new value of rotation
//     */
//    public void setRotation(float rotation)
//    {
//        super.setRotation(rotation);
//    }
//
//    /**
//     * Set the z-index for rendering
//     *
//     * @param layer new value of layer
//     */
//    public void setLayer(int layer)
//    {
//        super.setLayer(layer);
//    }
//
//    /**
//     * Add a component to this entity
//     * @param comp 
//     */
//    public void addComponent(Component comp)
//    {
//        super.addComponent(comp);
//    }
//
//    /**
//     * Removes a component from this entity
//     * @param name The name of the component to remove
//     * @return The removed component, or null if no matching name was found
//     */
//    public Component removeComponent(String name)
//    {
//        super.removeComponent(name);
//    }
//
//    /**
//     * This method corresponds to the update() method in the Game interface
//     * @see org.newdawn.slick.Game
//     * @param container
//     * @param delta
//     * @throws SlickException
//     */
//    public final void update(GameContainer container, int delta) throws SlickException
//    {
//        //Iterate through collection of Components,
//        //calling update on each
//
//        super.update(container, delta);
//    }
//
//    /**
//     * This method corresponds to the init() method in the Game interface
//     * @see org.newdawn.slick.Game
//     * @param container
//     * @throws SlickException
//     */
//    public final void init(GameContainer container) throws SlickException
//    {
//        //Iterate through collection of Components,
//        //calling init on each
//
//        super.init(container);
//    }
//
//    /**
//     * This method corresponds to the render() method in the Game interface
//     * @see org.newdawn.slick.Game
//     * @param container
//     * @param g
//     * @throws SlickException
//     */
//    public final void render(GameContainer container, Graphics g) throws SlickException
//    {
//        //Iterate through collection of Components,
//        //calling render on each RenderableComponent
//
//        super.render(container, g);
//    }
//
//    /**
//     * Allows a subclass to override and provide custom update functionality
//     * @param container
//     * @param delta
//     */
//    protected void customUpdate(GameContainer container, int delta)
//    {
//        //Override in subclasses to allow an Entity to do something in the update cycle
//    }
//
//    /**
//     * Allows a subclass to override and provide custom init functionality
//     * @param container
//     */
//    protected void customInit(GameContainer container)
//    {
//        //Override in subclasses to allow an Entity to do something in the init cycle
//    }
//
//    /**
//     * Allows a subclass to override and provide custom render functionality
//     * @param container
//     * @param g
//     */
//    protected void customRender(GameContainer container, Graphics g)
//    {
//        //Override in subclasses to allow an Entity to do something in the render cycle
//    }
//}
