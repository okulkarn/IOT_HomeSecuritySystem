# IOT_HomeSecuritySystem

IOT project: Home Security System using Sensors
	Server : Keeps listening on a particular port and IP address continuously and serves the client's requests
		We maintaned the database on the cloud(XAMP/LAMP). The Android App user updates the database.
		we communicated with the simulated actuators/sensors(database in pi) and not used actual hardware for sensors. 
		We simulated different actuators like thermostat(controlled the temp),light dimmer,garage door actuator,motion detector, lock sensor etc
		All communication with these actuator's simulation was multi-threaded(ran simultaneously) in Java(PC) which served the client's requests.
		runs nmap shell script file and obtains the IP adresses of all the pis on the network and connects the raspberry pi to the server. 
		This acted as a server(PC).
		
	Client : Requests the server for the information.  in this project
		raspberry pi is the controller to which actuators and sensors (we have used database instead of actual h/w:actuators/sensors) are connected
		raspberry pi is the client. we maintain a database in the pi(acts as/analogous to/instead of actual hardware-sensors and actuators). 
		//runs nmap shell script file and obtains the IP adresses of all the devices on the network and connects the itself to the server. 
		Requests the server for the information/commands(eg open door,dim light) and updates the database in it.
		
	The socket communication is between the main database on the cloud(controlled by computer as server) and the pi:sensors and actuators(client).
	*currently mainly actuators are used in this project(no sensors). So they ask the users for the commands and the app/database/server 
	serves the commands(open door, dim light, increase temp etc) continuously.
	Developed an android App for the home security system monitoring. the main database(lamp/xamp) is controlled through the app (no need 
	of this though, we can directly control the actuators through the app and no need of the middle(main) database).
	The java code reads the main database continuously(in multi-threaded way) and accordings gives commands to the actuators.
