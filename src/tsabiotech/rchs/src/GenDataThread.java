package tsabiotech.rchs.src;

import java.util.Random;

public class GenDataThread extends Thread {
	
	private int times, interval;
	private Astronaut client;
	
	public GenDataThread(Astronaut client, int times, int interval) {
		this.times = times;
		this.interval = interval;
		this.client = client;
	}
	
	@Override
	public void run() {
		Log.write("Generating random data run:" + times + ", interval:" + interval);
		for(int i = 0; i < times; i++) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		}
	}

}
