/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omkar
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class cli extends Thread {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    
    public Socket run(String[] args) throws IOException {
        /*String serverAddress = JOptionPane.showInputDialog(
            "Enter IP Address of a machine that is\n" +
            "running the date service on port 8899:");*/
        //Socket s = new Socket("192.168.1.143", 8799);
        Socket s = new Socket("10.0.0.2", 8799);
        
        String answer = "";
        
        //while(true)
        {
        //System.out.println("i am here");
 
        /*BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        answer = input.readLine();*/
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        //System.out.println(s.getLocalPort());
        out.println(new Date().toString());
        out.println("yo man");
        System.out.println(answer);
        //System.out.println("ok");
        answer = "";
        //JOptionPane.showMessageDialog(null, answer);
        //System.exit(0);
        return(s);
        }
    }
}