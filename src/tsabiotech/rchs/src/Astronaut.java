package tsabiotech.rchs.src;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Astronaut {
	
	private Client client;
	public String id, name;
	
	//Astronaut define method
	public Astronaut() {
		client = new Client();
		client.start();
		Kryo kryo = client.getKryo();
		kryo.register(Register.class);
		kryo.register(SensorUpdate.class);
		Log.write("Started client!");
		listen();
	}
	
	//Connect to an external monitoring server by IP address
	public void connect(String ip) throws Exception {
		Log.write("Connecting to " + ip);
		client.connect(5000, ip, Network.TCP_PORT);
		
		if(client.isConnected()) {
			Log.write("Successfully connected!");
		} else {
			Log.write("Connection attempt failed!");
		}
	}
	
	//Add a listener for the client object to wait for incoming data and act based on the contents
	public void listen() {
		Log.write("Started client data listening!");
		client.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		          if(object instanceof Register) {
		        	  Register r = (Register)object;
		        	  
		        	  if(r.code == 0x01) {
		        		  id = r.id;
		        		  name = r.name;
		        		  Log.write(r.name + " is now register as an astronaut");
		        	  }
		          }
		       }
		    });
	}
	
	//Simple method, send an object to the connected server
	public void send(Object o) {
		client.sendTCP(o);
	}

}
