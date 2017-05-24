
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.ResultSet.TYPE_SCROLL_SENSITIVE;
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
public class DoorWinSens extends Thread{

    private int windoorsta;
    public Connection con1;
    public Socket s;
    
    public DoorWinSens(int windoorsta,Connection con1, Socket s){
        this.windoorsta = windoorsta;
        this.con1 =con1;
        this.s = s;
    }
    
    @Override
    public void run(){
        Random randomGenerator = new Random();
        //System.out.println("doorwin");
        try {
            
            while(true){

                Statement stm2 = con1.createStatement(TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
                String SQL1 = "SELECT * FROM Door_Window";
            //ResultSet.CLOSE_CURSORS_AT_COMMIT;
                ResultSet rs1 = stm2.executeQuery(SQL1);

                rs1.first();
                String floor = rs1.getString("Floor");
                String stat = rs1.getString("Stat");
            
                String SQL0 = "update group12.door_window set Floor =" +  "'" + floor + "'" + ", Stat =" + "'" + stat + "'";
            //String SQL0 ="UPDATE iot.door_window" + "VALUES ('one', 'open')\n" + "";

                try {

                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    out.println(SQL0);
                    
                    //
                
                    windoorsta = randomGenerator.nextInt(5);
                    Thread.sleep(500);
/*                    if(windoorsta == 1){
                         
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL0);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL0);
                //rs0.next();
                    }
                    
                    
                    if(windoorsta == 2){
                        
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL1);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL1);
                    }

                    if(windoorsta == 3){
                         
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL2);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL2);
                        //rs0.next();
                    }
                    
                    
                    if(windoorsta == 4){
                        
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL3);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL3);
                        //
                    }*/
                    /*if(windoorsta == false)
                    windoorsta = true;
                    
                    if(windoorsta == true)
                    windoorsta = false;*/
                    //Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DoorWinSens.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DoorWinSens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoorWinSens.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int getwinstatus(){
        return windoorsta;
    }
    
}
