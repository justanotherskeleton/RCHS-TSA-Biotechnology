package tsabiotech.rchs.src;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Astronaut {
	
	private Client client;
	public String id, name;
	
	public Astronaut() {
		client.start();
		Log.write("Started client!");
		listen();
	}
	
	public void connect(String ip) throws Exception {
		Log.write("Connecting to " + ip);
		client.connect(5000, ip, Network.TCP_PORT);
		
		if(client.isConnected()) {
			Log.write("Successfully connected!");
		} else {
			Log.write("Connection attempt failed!");
		}
	}
	
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
	
	public void send(Object o) {
		client.sendTCP(o);
	}

}
