
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
public class groundlight extends LightSens{
    
    int sbar;
    boolean li;
    public Connection con1;
    public Socket s;
    
    public groundlight(int sbar, boolean li,Connection con1, Socket s) {
        super(sbar);
        this.sbar = sbar;
        this.li = li;
        this.con1 = con1;
        this.s = s;
    }
    
    @Override
    public void run(){
        try {
            //Statement stm2 = con1.createStatement(TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
            while(true){
                if(li == true){
                    this.sbar = randomGenerator.nextInt(100);
                    String SQL1 ="update group12.Lights set Stat = 'on', Floor = 'Upstairs', Dimmer_Level ="  + "'" + Integer.toString(sbar) + "'";
                    PreparedStatement preparedStmt = con1.prepareStatement(SQL1);
                    preparedStmt.executeUpdate();
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    out.println("update group12.lights set Stat = 'on', Floor = 'Upstairs', Dimmer_Level ="  + "'" + Integer.toString(sbar) + "'");

                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(groundlight.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(groundlight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(groundlight.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getsbar(){
        return sbar;
    }

}
