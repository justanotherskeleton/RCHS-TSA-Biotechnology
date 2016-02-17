package tsabiotech.rchs.src;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
		setBounds(100, 100, 585, 118);
		setVisible(true);
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
		lblBT.setBounds(239, 29, 176, 14);
		contentPane.add(lblBT);
		
		lblBTID = new JLabel("(incrase/decrease)");
		lblBTID.setBounds(236, 46, 129, 14);
		contentPane.add(lblBTID);
		
		lblAdrenaline = new JLabel("Adrenaline: (level)");
		lblAdrenaline.setBounds(411, 29, 116, 14);
		contentPane.add(lblAdrenaline);
		
		lblAdrenalineID = new JLabel("(incrase/decrease)");
		lblAdrenalineID.setBounds(411, 46, 90, 14);
		contentPane.add(lblAdrenalineID);
		
		lblStatus = new JLabel("Status: Nominal");
		lblStatus.setBounds(462, 11, 97, 14);
		contentPane.add(lblStatus);
		
		JLabel[] labels = { lblBpm, lblBPMID, lblBloodPressure, lblBloodPressureID, lblBT, lblBTID, 
				lblAdrenaline, lblAdrenalineID };
		
		ViewUpdateThread vut = new ViewUpdateThread(id, labels, this);
		new Thread(vut).start();
		
	}
}
