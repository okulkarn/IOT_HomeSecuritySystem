
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omkar
 */
public class DeviceList {
    
    ArrayList<Device> deviceList;
    
    DeviceList(){
        deviceList = new ArrayList<Device>();
    }
    
    void addDevice(Device dev){
        this.deviceList.add(dev);
    }
    
    
}

class Device{
    String ip;
    String role;
    
    Device(String ip, String role){
        this.ip = ip;
        this.role = role;
    }
    
    @Override
    public String toString(){
        return ip + " = " + role;
    }
}