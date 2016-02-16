package tsabiotech.rchs.src;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.lang.RandomStringUtils;

public class Database {
	
	public HashMap<String, String> ids = new HashMap<String, String>();
	public LinkedList<String> astronauts = new LinkedList<String>();
	
	public void newAstronaut(String name) {
		ids.put(RandomStringUtils.randomAlphanumeric(8).toUpperCase(), name);
		astronauts.add(name);
		Log.write("Added new astronaut " + name + "!");
	}
	
	public void removeAstronaut(String id) {
		ids.remove(id);
		Log.write("Remove astronaut with id:" + id + "!");
	}
	
}
