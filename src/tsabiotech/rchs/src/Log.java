package tsabiotech.rchs.src;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	//Write data to System.out
	public static void write(String output) {
		System.out.println("[" + Log.getTimestamp() + "] " + output);
	}
	
	//Get a simple timestamp (hours:minutes:seconds)
	public static String getTimestamp() {
		return new String(new SimpleDateFormat("HH.mm.ss").format(new Date()));
	}
	
	//Get a full timestamp (year.month.day hours:minutes:seconds)
	public static String getFullTimestamp() {
		return new String(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
	}

}
