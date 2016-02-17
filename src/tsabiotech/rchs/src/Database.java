package tsabiotech.rchs.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.lang.RandomStringUtils;

public class Database {
	
	public static File log_folder, running_dir;
	public static int times_updated = 0;
	
	public static HashMap<String, String> ids = new HashMap<String, String>();
	public static LinkedList<String> astronauts = new LinkedList<String>();
	public static HashMap<String, FileOutputStream> log_streams = new HashMap<String, FileOutputStream>();
	public static HashMap<String, SensorUpdate> liveData = new HashMap<String, SensorUpdate>();
	public static HashMap<String, SensorUpdate> lastData = new HashMap<String, SensorUpdate>();
	
	public static void init() throws Exception {
		running_dir = new File(Database.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		log_folder = new File(running_dir.getAbsolutePath() + "/logs");
		if(!log_folder.exists()) {
			Log.write("Creating log folder!");
			log_folder.mkdir();
		}
		
		Log.write("Initialized database functionality!");
	}
	
	public static String newAstronaut(String name) throws Exception {
		String id = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
		ids.put(id, name);
		astronauts.add(name);
		File new_log = new File(log_folder + "/" + id + ".log");
		new_log.createNewFile();
		log_streams.put(id, new FileOutputStream(new_log));
		Log.write("Added new astronaut " + name + "!");
		return id;
	}
	
	public static void removeAstronaut(String id) {
		ids.remove(id);
		Log.write("Remove astronaut with id:" + id + "!");
	}
	
	public static void appendData(SensorUpdate su) throws Exception {
		//Log.write("Appending new data for " + su.identifier);
		
		if(times_updated == 1) {
			lastData.put(su.identifier, liveData.get(su.identifier));
		} else if(times_updated > 1) {
			lastData.replace(su.identifier, liveData.get(su.identifier));
		}
		
		if(!liveData.containsKey(su.identifier)) {
			//Log.write("live data update with put()");
			liveData.put(su.identifier, su);
		} else {
			//Log.write("live data update with replace()");
			liveData.replace(su.identifier, su);
		}
		
		FileOutputStream fos = log_streams.get(su.identifier);
		String append = su.timestamp + ":::" + su.data_bpm + ":::" + su.data_body_heat + ":::" +
				su.data_bp_top + ":::" + su.data_bp_bottom + ":::" + su.data_hormone_ad;
		fos.write(append.getBytes(Charset.forName("UTF-8")));
		times_updated++;
	}
	
}
