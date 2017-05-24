
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omkar
 */
public class SendDeviceList extends Thread{
    DeviceList devList;
    Connection con;
    Socket s;
    
    SendDeviceList(DeviceList devList,Connection con, Socket s){
        this.devList = devList;
        this.con = con;
        this.s = s;
    }
    
    @Override
    public void run(){
        while(true){
            String data="data:";
            for (Device dev: devList.deviceList){
                data += dev+"\n";
            }
            try{
                //System.out.println("Sending data "+data);

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                out.println(data);
                Thread.sleep(1000);
            }catch (Exception ex) {
                System.out.println("SendDeviceList.run()");
            }
        }
    }
}
