
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class Sensors {

    Connection con;
    Connection con1;

    public Sensors(Connection con, Connection con1) {
        this.con = con;
        this.con1 = con1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        int flag = 0;
        while (true) {
            try {
                // TODO code application logic here
                //Sensors s = new Sensors();
                //runshellscript();
                //Socket s = new Socket("192.168.1.143", 8799);
                //Socket s = new Socket("10.0.0.2", 8799);
                //System.out.print("");
    
//Acting as a Server which keeps listening to the clients requests i.e RaspberryPi 

                Socket s;
                ServerSocket listener = new ServerSocket(8999);

                System.out.println("here");
                //listener.
                
                DeviceList devList = new DeviceList();
                
                System.out.println("waiting for connection");
                s = listener.accept();
                //System.out.println("here");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String role = br.readLine();
                Device dev = new Device(s.getInetAddress().toString(), role);
                devList.addDevice(dev);
//                System.out.print(s.getInetAddress() + " : " + role);

                if (flag == 0) {
                    String host = "jdbc:derby://localhost:1527/sensor";
                    String uName = "group12";
                    String password = "abcd";
                    Connection con1 = DriverManager.getConnection(host, uName, password);

                    //Class.forName("com.mysql.jdbc.Driver");
                    String host1 = "jdbc:mysql://localhost:3306/iot?zeroDateTimeBehavior=convertToNull";
                    //String host1 = "jdbc:mysql://192.168.43.144:3306/iot?zeroDateTimeBehavior=convertToNull";
                    String uName1 = "root";
                    String password1 = "";
                    //Connection con1 = DriverManager.getConnection(host1,uName1,password1);

                    LightSens ml = new mainlight(10, true, con1, s);
                    LightSens l = new LightSens(10);
                    LightSens gl = new groundlight(10, true, con1, s);

                    GarDoorSens gar = new GarDoorSens(1, con1, s); 
                    DoorWinSens win = new DoorWinSens(1, con1, s);
                    Thermostat ther = new Thermostat(con1, s);
                    MotionDet mot = new MotionDet(1, con1, s);
                    SendDeviceList dev1 = new SendDeviceList(devList, con1, s);
                    /*mot.start();
                    ml.start();
                    //gl.start();
                    ther.start();
                    win.start();*/
                    String s1 = "";
                    //s1 = s.getInputStream();
                    gar.start();
                    dev1.start();
                    flag = 1;
                }

//            while(true){
//                
//                Scanner in = new Scanner( System.in );
//                String choice;
//                choice = in.nextLine();
//                
//                switch(choice){
//                    
//                    case "main_light":
//                    {
//                        System.out.println(ml.getsbar());
//                        break;
//                    }
//                    
//                    case "ground_light":
//                    {
//                        System.out.println(gl.getsbar());
//                        break;
//                    }
//                    
//                    
//                    case "garage_status":
//                    {
//                        System.out.println(gar.getstatus());
//                        break;
//                    }
//                    
//                    case "win_status":
//                    {
//                        System.out.println(win.getwinstatus());
//                        break;
//                    }
//                    
//                    case "thermo_status":
//                    {
//                        System.out.println("");
//                        break;
//                    }
//                    
//                    default:
//                    {
//                        break;
//                    }
//                    
//                }
//            }
            
            } catch (Exception e) {
                
                System.out.println("sensors.java");
                System.out.println(e.getMessage());
            }
        }
    }

}
