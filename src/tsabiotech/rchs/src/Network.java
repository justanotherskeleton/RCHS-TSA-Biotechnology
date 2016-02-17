package tsabiotech.rchs.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Network {
	
	public static final int TCP_PORT = 54555;
	public static String MY_IP;;
	
	//Must be ran on start! Determines external ip!
	public static void init() throws Exception {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine();
		MY_IP = ip;
	}

}
