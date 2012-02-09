/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mrfornal.mp;

/**
 *
 * @author pham266693
 */
public class TDEntityManager
{
    
    public static TDEntityManager singleton;


    public static TDEntityManager getInstance()
    {
        if (singleton != null)
        {
            return singleton;
        }
        singleton = new TDEntityManager();
        return singleton;
    }

}
