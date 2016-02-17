package tsabiotech.rchs.src;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewUpdateThread implements Runnable {
	
	private JLabel[] labels;
	private JFrame frame;
	private String id;
	
	public ViewUpdateThread(String id, JLabel[] labels, JFrame frame) {
		this.id = id;
		this.labels = labels;
		this.frame = frame;
	}
	
	@Override
	public void run() {
		
		Log.write("Starting view update thread : " + id);
		while(Run.REFRESH) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Log.write("id:" + id + " -updating");
			SensorUpdate su = Database.liveData.get(id);
			SensorUpdate last = Database.lastData.get(id);
	   	 	
			labels[0].setText("BPM: " + su.data_bpm);
			if(su.data_bpm > last.data_bpm) {
				labels[1].setText("Increased");
			} else if(su.data_bpm == last.data_bpm) {
				labels[1].setText("Stable");
			} else if(su.data_bpm < last.data_bpm) {
				labels[1].setText("Decreased");
			}
			
			double bpr = su.data_bp_top / su.data_bp_bottom;
			double bprl = last.data_bp_top / last.data_bp_bottom;
			labels[2].setText("Blood Pressure: " + su.data_bp_top + "/" + su.data_bp_top);
			if(bpr > bprl) {
				labels[3].setText("Increased");
			} else if(bpr == bprl) {
				labels[3].setText("Stable");
			} else if(bpr < bprl) {
				labels[3].setText("Decreased");
			}
			
			labels[4].setText("Blood Temperature: " + round(su.data_body_heat, 2) + "F");
			if(su.data_body_heat > last.data_body_heat) {
				labels[5].setText("Increased");
			} else if(su.data_body_heat == last.data_body_heat) {
				labels[5].setText("Stable");
			} else if(su.data_body_heat < last.data_body_heat) {
				labels[5].setText("Decreased");
			}
			
			labels[6].setText("Addrenaline: " + su.data_hormone_ad);
			if(su.data_hormone_ad > last.data_hormone_ad) {
				labels[7].setText("Incrased");
			} else if(su.data_hormone_ad == su.data_hormone_ad) {
				labels[7].setText("Stable");
			} else if(su.data_hormone_ad < su.data_hormone_ad) {
				labels[7].setText("Decreased");
			}
			
			frame.revalidate();
			frame.repaint();
		}
	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
