
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.ResultSet.TYPE_SCROLL_SENSITIVE;
import java.sql.SQLException;
import java.sql.Statement;
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
public class mainlight extends LightSens{
    
    int sbar;
    boolean li;
    public Connection con1;
    public Socket s;

    public mainlight(int sbar, boolean li, Connection con1, Socket s) {
        super(sbar);
        this.sbar = sbar;
        this.li = li;
        this.con1 = con1;
        this.s = s;
    }
    
    @Override
    public void run(){
        try {
            Statement stm1 = con1.createStatement(TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
            
            while(true){
                String SQL1 = "SELECT * FROM Lights";
            //ResultSet.CLOSE_CURSORS_AT_COMMIT;
                ResultSet rs1 = stm1.executeQuery(SQL1);

                rs1.first();
                String floor = rs1.getString("Floor");
                String stat = rs1.getString("Stat");
                String dimmer_level = rs1.getString("Dimmer_Level");
                
                //if(stat == "on")
                {
                    this.sbar = randomGenerator.nextInt(10);
                    //String SQL0 ="INSERT INTO GROUP12.LIGHT (FLOOR, DIMMER_LEVEL)\n" + "VALUES ('main'," + Integer.toString(sbar)+"\n" + "";
                    //li = false;
                    /*String SQL0 ="update group12.Lights set Stat = 'on', Floor = 'Main', Dimmer_Level = " + "'" + Integer.toString(sbar) + "'";
                    PreparedStatement preparedStmt = con1.prepareStatement(SQL0);
                    preparedStmt.executeUpdate();*/
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    out.println("update group12.lights set Stat =" + "'" + stat + "'" + ", Floor =" + "'" + floor + "'" + ", Dimmer_Level = " + "'" + dimmer_level + "'");
                }
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(mainlight.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainlight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mainlight.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getsbar(){
        return sbar;
    }

}
