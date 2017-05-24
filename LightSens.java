/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.jndi.ldap.Connection;
import java.util.Random;

/**
 *
 * @author omkar
 */
public class LightSens extends Thread{
    
    private int sbar;
    Random randomGenerator = new Random();

    public LightSens(int sbar){
        
        this.sbar = sbar;
    }
        
    public int getsbar(){
        return sbar;
    }
    

}
