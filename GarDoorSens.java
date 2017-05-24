
//import static com.oracle.webservices.internal.api.databinding.DatabindingModeFeature.ID;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.ResultSet.TYPE_SCROLL_SENSITIVE;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omkar
 */
public class GarDoorSens extends Thread{

    private int doorsta;
    public Connection con1;
    public Socket s;
    
    public GarDoorSens(int doorsta,Connection con1, Socket s){
        this.doorsta = doorsta;
        this.con1 =con1;
        this.s = s;
    }
    
    @Override
    public void run(){
        Random randomGenerator = new Random();
        //System.out.println("in run");
        try {
            
/*            String SQL0 = "update group12.garage_door set Door = 1, Stat = 'open'";
            //String SQL0 ="UPDATE iot.door_window" + "VALUES ('one', 'open')\n" + "";
            String SQL1 = "update group12.garage_door set Door = 1, Stat = 'closed'";
            String SQL2 = "update group12.garage_door set Door = 2, Stat = 'open'";
            String SQL3 = "update group12.garage_door set Door = 2, Stat = 'closed'";*/
            
/*            ResultSet rs0 = stm.executeQuery( SQL3 );
            rs0.last();
            int sz = rs0.getRow();
            int i = 0;
            rs0.first();*/
             //ResultSet rs1 = stmt1.executeQuery( SQL1 );
            //stmt.executeUpdate( SQL0 );
            //String start = rs1.getString("Start_City");
            //String end = rs1.getString("End_City");
            while(true){

                Statement stm2 = con1.createStatement(TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
                String SQL1 = "SELECT * FROM Garage_Door";
            //ResultSet.CLOSE_CURSORS_AT_COMMIT;
                ResultSet rs1 = stm2.executeQuery(SQL1);

                rs1.first();
                int door = rs1.getInt("Door");
                String stat = rs1.getString("Stat");
            
                String SQL0 = "update group12.door_window set Door =" + door + ", Stat =" + "'" + stat + "'";
            //String SQL0 ="UPDATE iot.door_window" + "VALUES ('one', 'open')\n" + "";
                
                doorsta = randomGenerator.nextInt(5);
                try {    
                    Thread.sleep(1500);
                    //System.out.println("sending");
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    
                    out.println("query:"+SQL0);
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    if(br.readLine() == null){
                        System.out.println("disconnected");
                        //break;
                    }
                        
                    //out.
                    
/*                    if(doorsta == 1){
                         
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL0);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL0);
                    //rs0.next();
                    }
                    
                    
                    if(doorsta == 2){
                        
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL1);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL1);
                        //
                    }

                    if(doorsta == 3){
                         
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL2);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL2);
                        //rs0.next();
                    }
                    
                    
                    if(doorsta == 4){
                        
                        PreparedStatement preparedStmt = con1.prepareStatement(SQL3);
                        preparedStmt.executeUpdate();
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                        out.println(SQL3);
                        //
                    }*/
                    //rs0.next();
                } catch (InterruptedException ex) {
                    //System.out.println("disconnected");
                    Logger.getLogger(GarDoorSens.class.getName()).log(Level.SEVERE, null, ex);
                }catch (Exception e){
                    
                    System.out.println("disconnected");
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GarDoorSens.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int getstatus(){
        return doorsta;
    }

}
