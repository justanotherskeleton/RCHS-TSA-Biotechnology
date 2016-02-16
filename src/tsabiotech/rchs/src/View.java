package tsabiotech.rchs.src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class View extends JFrame {

	private JPanel contentPane;

	public View(String id) {
		String astronaut_name = Database.ids.get(id);
		setTitle("DATA: " + astronaut_name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAstronautName = new JLabel("Astronaut name");
		lblAstronautName.setBounds(10, 11, 140, 14);
		contentPane.add(lblAstronautName);
		
		JLabel lblBpmbpm = new JLabel("BPM: (bpm)");
		lblBpmbpm.setBounds(20, 29, 75, 14);
		contentPane.add(lblBpmbpm);
		
		JLabel lblincreasedecrease = new JLabel("2 (increase/decrease");
		lblincreasedecrease.setBounds(20, 46, 75, 14);
		contentPane.add(lblincreasedecrease);
		
		JLabel lblBloodPressurebp = new JLabel("Blood Pressure: (bp)");
		lblBloodPressurebp.setBounds(118, 29, 111, 14);
		contentPane.add(lblBloodPressurebp);
		
		JLabel lblincrasedecresae = new JLabel("(incrase/decresae)");
		lblincrasedecresae.setBounds(118, 46, 75, 14);
		contentPane.add(lblincrasedecresae);
		
		JLabel lblNewLabel = new JLabel("Body Temperature: (ff)f");
		lblNewLabel.setBounds(239, 29, 133, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblincrasedecrease = new JLabel("(incrase/decrease)");
		lblincrasedecrease.setBounds(236, 46, 111, 14);
		contentPane.add(lblincrasedecrease);
		
		JLabel lblAdrenaline = new JLabel("Adrenaline: (level)");
		lblAdrenaline.setBounds(382, 29, 116, 14);
		contentPane.add(lblAdrenaline);
		
		JLabel lblincrasedecrease_1 = new JLabel("(incrase/decrease)");
		lblincrasedecrease_1.setBounds(382, 46, 90, 14);
		contentPane.add(lblincrasedecrease_1);
		
		JLabel lblStatusstatus = new JLabel("Status: (STATUS)");
		lblStatusstatus.setBounds(401, 11, 97, 14);
		contentPane.add(lblStatusstatus);
	}
}
