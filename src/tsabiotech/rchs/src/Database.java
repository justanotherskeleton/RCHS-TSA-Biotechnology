package tsabiotech.rchs.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.lang.RandomStringUtils;

public class Database {
	
	public static File log_folder, running_dir;
	
	public static HashMap<String, String> ids = new HashMap<String, String>();
	public static LinkedList<String> astronauts = new LinkedList<String>();
	public static HashMap<String, FileOutputStream> log_streams = new HashMap<String, FileOutputStream>();
	public static HashMap<String, SensorUpdate> liveData = new HashMap<String, SensorUpdate>();
	
	public static void init() throws Exception {
		running_dir = new File(Database.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		log_folder = new File(running_dir.getAbsolutePath() + "/logs");
		if(!log_folder.exists()) {
			Log.write("Creating log folder!");
			log_folder.mkdir();
		}
		
		Log.write("Initialized database functionality!");
	}
	
	public void newAstronaut(String name) throws Exception {
		String id = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
		ids.put(id, name);
		astronauts.add(name);
		File new_log = new File(log_folder + "/" + id + ".log");
		new_log.createNewFile();
		log_streams.put(id, new FileOutputStream(new_log));
		Log.write("Added new astronaut " + name + "!");
	}
	
	public void removeAstronaut(String id) {
		ids.remove(id);
		Log.write("Remove astronaut with id:" + id + "!");
	}
	
	public void appendData(SensorUpdate su) {
		
	}
	
}
