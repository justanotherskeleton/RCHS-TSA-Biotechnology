package tsabiotech.rchs.src;

import java.util.Random;
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
			
			if(s.startsWith("register")) {
				Register r = new Register();
				r.code = 0x00;
				r.id = null;
				r.name = split[1];
				client.send(r);
				Log.write("Sent registration request for " + r.name);
			}
			
			if(s.startsWith("connect")) {
				String ip = split[1];
				client.connect(ip);
			}
			
			if(s.startsWith("display")) {
				String id = split[1];
				View view = new View(id);
			}
			
			if(s.startsWith("gendata")) {
				if(split.length == 3) {
					int it = Integer.parseInt(split[1]);
					int interval = Integer.parseInt(split[2]);
					GenDataThread gdt = new GenDataThread(client, it, interval);
					gdt.run();
				}
				
				Random r = new Random();
				int bpm = r.nextInt((200 - 65) + 1) + 65;
				int bpt = r.nextInt((130 - 40) + 1) + 40;
				int bpb = r.nextInt((100 - 30) + 1) + 30;
				double bptemp = 97 + (99 - 97) * r.nextDouble();
				int ad = r.nextInt((100 - 10) + 1) + 10;
				SensorUpdate su = new SensorUpdate();
				su.data_body_heat = bptemp;
				su.data_bpm = bpm;
				su.data_bp_top = bpt;
				su.data_bp_bottom = bpb;
				su.data_hormone_ad = ad;
				su.identifier = client.id;
				su.timestamp = Log.getFullTimestamp();
				client.send(su);
				Log.write("Generated random data!");
			}
			
			if(s.equalsIgnoreCase("client name")) {
				Log.write("Your name is " + client.name);
			}
			
			if(s.equalsIgnoreCase("client id")) {
				Log.write("Your id is " + client.id);
			}
			
			if(s.equalsIgnoreCase("ip")) {
				Log.write(Network.MY_IP);
			}
			
			if(s.equalsIgnoreCase("exit")) {
				Log.write("Goodbye!");
				System.exit(0);
			}
			
			if(s.equalsIgnoreCase("localstart")) {
				client = new Astronaut();
				server = new Receiver();
				client.connect("127.0.0.1");
				Register r = new Register();
				r.code = 0x00;
				r.id = null;
				r.name = "John";
				client.send(r);
				Log.write("Sent registration request for " + r.name);
			}
			
			if(s.startsWith("datacheck")) {
				String id = split[1];
				boolean liveG = false, lastG = false;
				if(Database.liveData.get(id) != null) {
					liveG = true;
				}
				
				if(Database.lastData.get(id) != null) {
					lastG = true;
				}
				
				Log.write("Data check: live-" + liveG + " | last-" + lastG);
				
			}
		}
	}

}
