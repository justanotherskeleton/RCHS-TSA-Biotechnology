package tsabiotech.rchs.src;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Receiver {
	
	private Server server;
	
	public Receiver() throws Exception {
		server = new Server();
		server.start();
		server.bind(Network.TCP_PORT);
		Log.write("Successfully started listening server!");
		listen();
	}
	
	public void listen() {
		Log.write("Listening for data!");
		server.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		          if (object instanceof SensorUpdate) {
		        	 SensorUpdate su = (SensorUpdate)object;
		        	 String name = Database.ids.get(su.identifier);
		        	 try {
						Database.appendData(su);
					} catch (Exception e) {
						e.printStackTrace();
					}
		          }
		          
		          if(object instanceof Register) {
		        	  Register r = (Register)object;
		        	  if(r.code == 0x0) {
		        		  try {
							String id = Database.newAstronaut(r.name);
							Register rr = new Register();
							rr.code = 0x01;
							rr.name = r.name;
							rr.id = id;
							server.sendToTCP(connection.getID(), rr);
						} catch (Exception e) {
							e.printStackTrace();
						}
		        	  }
		          }
		       }
		    });
	}

}
