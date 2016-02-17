package tsabiotech.rchs.src;

//This class is sent over the network and contains data from the sensors
public class SensorUpdate {
	
	public String identifier;
	public String timestamp;
	public int data_bpm;
	public int data_bp_top, data_bp_bottom;
	public double data_hormone_ad;
	public double data_body_heat;

}
