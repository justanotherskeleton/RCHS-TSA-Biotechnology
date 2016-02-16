package tsabiotech.rchs.src;

import java.util.Scanner;

public class Run {
	
	public static boolean REFRESH = true;
	public Receiver server;
	public Astronaut client;
	
	public static void main(String[] args) throws Exception {
		Database.init();
		Network.init();
		Run r = new Run();
		r.run();
	}
	
	public void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(REFRESH) {
			String s = scanner.nextLine();
			String[] split = s.split(" ");
			
			if(s.equalsIgnoreCase("start client")) {
				client = new Astronaut();
			}
			
			if(s.equalsIgnoreCase("start server")) {
				server = new Receiver();
			}
			
			if(s.startsWith("register astronaut")) {
				Register r = new Register();
				r.code = 0x00;
				r.id = null;
				r.name = split[2];
				client.send(r);
				Log.write("Sent registration request for " + r.name);
			}
			
			if(s.startsWith("client connect")) {
				String ip = split[2];
				client.connect(ip);
			}
			
			if(s.startsWith("display")) {
				String id = split[1];
				View v = new View(id);
			}
		}
	}

}
