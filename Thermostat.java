
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
import static java.sql.ResultSet.TYPE_SCROLL_SENSITIVE;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omkar
 */
public class Thermostat extends Thread{
    
    private float therm;
    float tempset;
    
    int i = 1;
    public Connection con1;
    public Socket s;
    
    public Thermostat(Connection con1, Socket s){
        this.con1 = con1;
        this.s = s;
    }
    
    @Override
    public void run(){
        
        try {
            float t;
            Statement stm3 = con1.createStatement(TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
            while(true)
            {
           
            String SQL1 = "SELECT * FROM thermostats";
            //ResultSet.CLOSE_CURSORS_AT_COMMIT;
            ResultSet rs1 = stm3.executeQuery(SQL1);
            //preparedStmt.setString(2, "Fred");
            
            //rs1.first();
            Random randomGenerator = new Random();
                int fl = randomGenerator.nextInt(2);
                rs1.first();
                String th = rs1.getString("Control_Temp");
                String th1 = rs1.getString("Current_Temp");
                //System.out.println(th);
                //System.out.println(th1);
                tempset = Float.parseFloat(th);
                therm = Float.parseFloat(th1);
                t = therm;
                
                //read();
                
                while(true)
                {
                    Random randomGenerator1 = new Random();
                    if(t>tempset)
                        t = (float) (t - 0.5);
                    if(t<tempset)
                        t = (float) (t + 0.5);
                    //System.out.println(tempset);
                    if(t == tempset){
                        //System.out.println("writing");
                        float  r = randomGenerator1.nextInt(30);
                        //System.out.println(r);
                        
                        String query = "update group12.thermostats set Current_Temp = " + "'" + Float.toString(t) + "'" + ",Control_Temp = " + "'" + Float.toString(r) + "'" + "where Floor = 'Main'";
                        PreparedStatement preparedStmt = con1.prepareStatement(query);
                
                        preparedStmt.executeUpdate();
                        preparedStmt.close();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println("update group12.thermostats set Current_Temp = " + "'" + Float.toString(t) + "'" + ",Control_Temp = " + "'" + Float.toString(r) + "'" + "where Floor = 'Main'");
                        //out.println(r);
//                        rs1.first();
                        /*rs1.updateString("Floor", "Main");
                        rs1.updateString("Mode_AC", "on");
                        rs1.updateString("Fan", "on");
                        rs1.updateString("Current_Temp", Float.toString(t));// + Float.toString(t) + "," + Float.toString(tempset));
                        //rs1.updateInt("Control_Temp", (int)tempset);
                        rs1.updateString("Control_Temp", Float.toString(randomGenerator.nextInt(30)));
                        //rs1.last();
                        rs1.updateRow();*/
                        //rs1.first();
                        
                        
                        break;
                        
                        //, Mode_AC, Fan, Current_Temp, Control_Temp", "Main,Cool,on,
                        /*String SQL2 ="INSERT INTO group12.thermostats (Floor, Mode_AC, Fan, Current_Temp, Control_Temp) \n" + "VALUES ('Main', 'Cool', 'on'," + "'" + Float.toString(t) + "'," + "'" + Float.toString(tempset) + "'" +  ")\n" + "";
                        stm3.executeUpdate( SQL2 );
                        String SQL3 ="INSERT INTO group12.thermostats (Floor, Mode_AC, Fan, Current_Temp, Control_Temp) \n" + "VALUES ('Main', 'Cool', 'on'," + "'" + Float.toString(t) + "'," + "'" + Float.toString(randomGenerator.nextInt(30)) + "'" +  ")\n" + "";
                        stm3.executeUpdate( SQL3 );*/                            
                    }
                     
                    //System.out.println(tempset);
                    
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                }
                
            }
        } catch (SQLException ex) {            
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void read() {
        Float f = new Float(0.0);
        try {
            {
                Statement stm4 = con1.createStatement(TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
                String SQL0 = "SELECT * FROM thermostats";
                ResultSet rs = stm4.executeQuery(SQL0);
                //if(i==1)
                rs.first();
                String th = rs.getString("Control_Temp");
                String th1 = rs.getString("Current_Temp");
                //System.out.println(th);
                tempset = Float.parseFloat(th);
                therm = Float.parseFloat(th1);
                //t = therm;
                //i = i + 1;
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(Thermostat.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return f;
    }
}
