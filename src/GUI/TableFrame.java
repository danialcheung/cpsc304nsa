package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import queries.Query;

public class TableFrame {
	
	private String buttonLabel;
	private String inputLabel;
	private Query query;
	private JFrame frame;
	
	public TableFrame(JTable table, String buttonLabel, String inputLabel, Query query) {
		this.buttonLabel = buttonLabel;
		this.inputLabel = inputLabel;
		this.query = query;
		this.frame = new JFrame();
		init(table);
	}
	
	public void init(JTable table) {
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.gridheight = 4;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(table, BorderLayout.CENTER);
		panel.add(tablePanel, c);

		JButton actionButton = new JButton();
		actionButton.setText(buttonLabel);
		actionButton.addActionListener(e -> actionAction(e));
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.SOUTH;
		c.weightx = 0;
		c.weighty = 0;
		panel.add(actionButton, c);

		JButton closeButton = new JButton();
		closeButton.setText("close");
		closeButton.addActionListener(e -> closeAction(e));
		c.fill = GridBagConstraints.NONE;
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.weightx = 0;
		c.weighty = 0;
		panel.add(closeButton, c);

		JTextField text = new JTextField();
		text.setText(inputLabel);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.SOUTHWEST;
		panel.add(text, c);

		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void actionAction(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	private void closeAction(ActionEvent e) {
		this.frame.dispose();
	}
	
}
