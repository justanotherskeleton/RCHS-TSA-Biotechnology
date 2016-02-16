package tsabiotech.rchs.src;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Receiver {
	
	private Server server;
	
	public Database database;
	
	public Receiver() throws Exception {
		server = new Server();
		server.start();
		server.bind(Network.TCP_PORT);
		Log.write("Successfully started listening server!");
	}
	
	public void listen() {
		Log.write("Listening for data!");
		server.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		          if (object instanceof SensorUpdate) {
		             Log.write("Sensor update received from ");
		          }
		       }
		    });
	}

}
