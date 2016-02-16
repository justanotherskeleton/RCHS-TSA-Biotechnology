package tsabiotech.rchs.src;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class View extends JFrame {

	private JPanel contentPane;
	public String id;
	public JLabel lblAstronautName, lblBpm, lblBPMID, lblBloodPressure, lblBloodPressureID, lblBT, lblBTID, 
	lblAdrenaline, lblAdrenalineID, lblStatus;

	public View(String id) throws Exception {
		this.id = id;
		String astronaut_name = Database.ids.get(id);
		setTitle("DATA: " + astronaut_name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAstronautName = new JLabel("Astronaut " + astronaut_name);
		lblAstronautName.setBounds(10, 11, 140, 14);
		contentPane.add(lblAstronautName);
		
		lblBpm = new JLabel("BPM: (bpm)");
		lblBpm.setBounds(20, 29, 75, 14);
		contentPane.add(lblBpm);
		
		lblBPMID = new JLabel("2 (increase/decrease");
		lblBPMID.setBounds(20, 46, 75, 14);
		contentPane.add(lblBPMID);
		
		lblBloodPressure = new JLabel("Blood Pressure: (bp)");
		lblBloodPressure.setBounds(118, 29, 111, 14);
		contentPane.add(lblBloodPressure);
		
		lblBloodPressureID = new JLabel("(incrase/decresae)");
		lblBloodPressureID.setBounds(118, 46, 75, 14);
		contentPane.add(lblBloodPressureID);
		
		lblBT = new JLabel("Body Temperature: (ff)f");
		lblBT.setBounds(239, 29, 133, 14);
		contentPane.add(lblBT);
		
		lblBTID = new JLabel("(incrase/decrease)");
		lblBTID.setBounds(236, 46, 111, 14);
		contentPane.add(lblBTID);
		
		lblAdrenaline = new JLabel("Adrenaline: (level)");
		lblAdrenaline.setBounds(382, 29, 116, 14);
		contentPane.add(lblAdrenaline);
		
		lblAdrenalineID = new JLabel("(incrase/decrease)");
		lblAdrenalineID.setBounds(382, 46, 90, 14);
		contentPane.add(lblAdrenalineID);
		
		lblStatus = new JLabel("Status: Nominal");
		lblStatus.setBounds(401, 11, 97, 14);
		contentPane.add(lblStatus);
		
		while(Run.REFRESH) {
			Thread.sleep(1000);
			updateData();
		}
	}
	
	//Refreshes the display with new data
	public void updateData() {
		SensorUpdate su = Database.liveData.get(id);
		SensorUpdate last = Database.lastData.get(id);
   	 	
		lblBpm.setText("BPM: " + su.data_bpm);
		if(su.data_bpm > last.data_bpm) {
			lblBPMID.setText("Increased");
		} else if(su.data_bpm == last.data_bpm) {
			lblBPMID.setText("Stable");
		} else if(su.data_bpm < last.data_bpm) {
			lblBPMID.setText("Decreased");
		}
		
		double bpr = su.data_bp_top / su.data_bp_bottom;
		double bprl = last.data_bp_top / last.data_bp_bottom;
		lblBloodPressure.setText("Blood Pressure: " + bpr);
		if(bpr > bprl) {
			lblBloodPressureID.setText("Increased");
		} else if(bpr == bprl) {
			lblBloodPressureID.setText("Stable");
		} else if(bpr < bprl) {
			lblBloodPressureID.setText("Decreased");
		}
		
		lblBT.setText("Blood Temperature: " + su.data_body_heat + "F");
		if(su.data_body_heat > last.data_body_heat) {
			lblBTID.setText("Increased");
		} else if(su.data_body_heat == last.data_body_heat) {
			lblBTID.setText("Stable");
		} else if(su.data_body_heat < last.data_body_heat) {
			lblBTID.setText("Decreased");
		}
		
		lblAdrenaline.setText("Addrenaline: " + su.data_hormone_ad);
		if(su.data_hormone_ad > last.data_hormone_ad) {
			lblAdrenalineID.setText("Incrased");
		} else if(su.data_hormone_ad == su.data_hormone_ad) {
			lblAdrenalineID.setText("Stable");
		} else if(su.data_hormone_ad < su.data_hormone_ad) {
			lblAdrenalineID.setText("Decreased");
		}
	}
}
