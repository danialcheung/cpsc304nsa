package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import queries.Projection;

public class MenuFrame {

	private Connection con;
	public MenuFrame(Connection con) {
		this.con = con;
		initComponents();
	}
	private void initComponents() {
		JFrame frame = new JFrame("Menu");
		frame.setLayout(new FlowLayout());
		
		JButton selectButton = new JButton();
		selectButton.setText("Data by country");
		selectButton.addActionListener(e -> selectAction(e));
		frame.add(selectButton);

		JButton projectButton = new JButton();
		projectButton.setText("Owners of devices by type");
		projectButton.addActionListener(e -> projectAction(e));
		frame.add(projectButton);

		JButton joinButton = new JButton();
		joinButton.setText("Data and associated owners");
		joinButton.addActionListener(e -> joinAction(e));
		frame.add(joinButton);

		JButton aggrButton = new JButton();
		aggrButton.setText("Suspicious data per country");
		aggrButton.addActionListener(e -> aggrAction(e));
		frame.add(aggrButton);

		JButton nestedAggrButton = new JButton();
		nestedAggrButton.setText("Country with min/max average transaction");
		nestedAggrButton.addActionListener(e -> nestedAggrAction(e));
		frame.add(nestedAggrButton);

		JButton deleteButton = new JButton();
		deleteButton.setText("Stop tracking a device");
		deleteButton.addActionListener(e -> deleteAction(e));
		frame.add(deleteButton);

		JButton updateButton = new JButton();
		updateButton.setText("Falsify evidence");
		updateButton.addActionListener(e -> updateAction(e));
		frame.add(updateButton);
		
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	private void updateAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	private void deleteAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	private void nestedAggrAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	private void aggrAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	private void selectAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	private void projectAction(ActionEvent e) {
		Projection p = new Projection(con);
		JTable table = p.projectOwnerOfDeviceType("cell phone");
		new TableFrame(table, "project", "device type");
	}
	
	private void joinAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
