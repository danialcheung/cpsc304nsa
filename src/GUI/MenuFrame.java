package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import queries.Aggregation;
import queries.Deletion;
import queries.Join;
import queries.NestedAggregationWithGroupBy;
import queries.Projection;
import queries.Selection;

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
		
		JButton divisionButton = new JButton();
		// TODO this whole thing
		divisionButton.setText("Some division query ???");
		divisionButton.addActionListener(e -> divisionAggrAction(e));
		frame.add(divisionButton);

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
	private Object divisionAggrAction(ActionEvent e) {
		// TODO Auto-generated method stub
		return null;
	}
	private void updateAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	private void deleteAction(ActionEvent e) {
		Deletion d = new Deletion(con);
		JTable table = d.selectAllDevices();
		new TableFrame(table, "delete", "device id", d);
	}
	
	private void nestedAggrAction(ActionEvent e) {
		NestedAggregationWithGroupBy n = new NestedAggregationWithGroupBy(con);
		Object[][] empty = {{"",""}};
		String[] header = {"country", "max_avg_suspicious_amount"};
		JTable table = new JTable(empty, header);
		new TableFrame(table, "count", "min/max", n); // TODO whip up a custom form with a min/max selector instead of a text input field
	}
	private void aggrAction(ActionEvent e) {
		Aggregation a = new Aggregation(con);
		Object[][] empty = {{"",""}};
		String[] header = {"country", "suspicious_data_count"};
		JTable table = new JTable(empty, header);
		new TableFrame(table, "count", "", a);
	}
	private void selectAction(ActionEvent e) {
		Selection s = new Selection(con);
		Object[][] empty = {{"","","","","",""}};
		String[] header ={"data_id", "date", "suspicious", "lat", "lng", "device_id"};
		JTable table = new JTable(empty, header);
		new TableFrame(table, "select", "country", s);
		}
	
	private void projectAction(ActionEvent e) {
		Projection p = new Projection(con);
		Object[][] empty = {{"",""}};
		String[] header = {"device_type", "owner"};
		JTable table = new JTable(empty, header);
		new TableFrame(table, "project", "device type", p);
	}
	
	private void joinAction(ActionEvent e) {
		Join j = new Join(con);
		Object[][] empty = {{"","","",""}};
		String[] header = {"owner", "data_id", "date", "suspicious"};
		JTable table = new JTable(empty, header);
		new TableFrame(table, "join", "", j);
	}
}
