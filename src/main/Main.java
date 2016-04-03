package main;

//We need to import the java.sql package to use JDBC
import java.sql.*;  
//for reading from the command line
import java.io.*;

//for the login window
import javax.swing.*;

import GUI.Login2;
import GUI.TableWindow;
import queries.Aggregation;
import queries.Deletion;
import queries.Division;
import queries.Join;
import queries.NestedAggregationWithGroupBy;
import queries.Projection;
import queries.Selection;
import queries.Update;

import java.awt.*;
import java.awt.event.*;

//for accessing tables in database


/*
 * This class implements a graphical login window and a simple text
 * interface for interacting with the branch table 
 */ 
public class Main implements ActionListener
{

	// command line reader 
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private Connection con;

	// user is allowed 3 login attempts
	private int loginAttempts = 0;

	// components of the login window
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JFrame mainFrame;

	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	/*
	 * constructs login window and loads JDBC driver
	 */ 
	public Main()
	{

		connect("root","luckey");
		
        Projection p = new Projection(con);
		JTable table = p.projectOwnerOfDeviceType("cell phone");

		openTableFrame(table, "project", "device type");
//		Login2 login = new Login2();
//		login.setVisible(true);

//		TableWindow t = new TableWindow();
//		t.setVisible(true);

		try 
		{
			// Load the MySQL driver
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	private void openTableFrame(JTable table, String buttonLabel, String inputLabel) {
		JFrame frame = new JFrame("Query");
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

	
	private void createMainFrame() {
		mainFrame = new JFrame("User Login");

		JLabel usernameLabel = new JLabel("Enter username: ");
		JLabel passwordLabel = new JLabel("Enter password: ");

		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');

		JButton loginButton = new JButton("Log In");

		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);


		// layout components using the GridBag layout manager

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// place the username label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(usernameLabel, c);
		contentPane.add(usernameLabel);

		// place the text field for the username
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		gb.setConstraints(usernameField, c);
		contentPane.add(usernameField);

		// place password label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 10, 0);
		gb.setConstraints(passwordLabel, c);
		contentPane.add(passwordLabel);

		// place the password field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 10, 10);
		gb.setConstraints(passwordField, c);
		contentPane.add(passwordField);

		// place the login button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(loginButton, c);
		contentPane.add(loginButton);

		// register password field and OK button with action event handler
		passwordField.addActionListener(this);
		loginButton.addActionListener(this);

		// anonymous inner class for closing the window
		mainFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		// size the window to obtain a best fit for the components
		mainFrame.pack();

		// center the frame
		Dimension d = mainFrame.getToolkit().getScreenSize();
		Rectangle r = mainFrame.getBounds();
		mainFrame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );
		
		mainFrame.setVisible(true);
		
		// place the cursor in the text field for the username
		usernameField.requestFocus();

	}

	/*
	 * connects to MySQL database named ug using user supplied username and password
	 */ 
	private boolean connect(String username, String password)
	{
		String connectURL = "jdbc:mysql://localhost:3306/nsa?useSSL=false";

		try 
		{
			con = DriverManager.getConnection(connectURL,username,password);

			System.out.println("\nConnected to MySQL!");
			return true;
		}
		catch (SQLException ex)
		{
			System.out.println("Message: " + ex.getMessage());
			return false;
		}
	}


	/*
	 * event handler for login window
	 */ 
	public void actionPerformed(ActionEvent e) 
	{
		if ( connect(usernameField.getText(), String.valueOf(passwordField.getPassword())) )
		{
			// if the username and password are valid, 
			// remove the login window and display a text menu 
			mainFrame.dispose();
			showMenu();    

		}
		else
		{
			loginAttempts++;

			if (loginAttempts >= 3)
			{
				mainFrame.dispose();
				System.exit(-1);
			}
			else
			{
				// clear the password
				passwordField.setText("");
			}
		}             
	}

    
	/*
	 * displays simple text interface
	 */ 
	private void showMenu()
	{
		int choice;
		boolean quit;

		quit = false;

		try 
		{
			// disable auto commit mode
			con.setAutoCommit(false);

			while (!quit)
			{
				System.out.print("\n\nPlease choose one of the following queries: \n");
				System.out.print("1.  Selection\n");
				System.out.print("2.  Projection\n");
				System.out.print("3.  Join\n");
				System.out.print("4.  Division\n");
				System.out.print("5.  Aggregation\n");
				System.out.print("6.  Nested aggregation with group-by\n");
				System.out.print("7.  Delete\n");
				System.out.print("8.  Update\n");
				System.out.print("9.  Quit\n>> ");

				choice = Integer.parseInt(in.readLine());

				System.out.println(" ");

				switch(choice)
				{
				case 1:  
					Selection selectObj = new Selection(con);
					selectObj.select(); 
					break;
				case 2: 
					Projection projectObj = new Projection(con);
					projectObj.project(); 
					break;
				case 3:  
					Join joinObj = new Join(con);
					joinObj.join(); 
					break;
				case 4:  
					Division divideObj = new Division(con);
					divideObj.divide(); 
					break;
				case 5:  
					Aggregation aggregateObj = new Aggregation(con);
					aggregateObj.aggregate(); 
					break;
				case 6:  
					NestedAggregationWithGroupBy nestedAggregateObj = new NestedAggregationWithGroupBy(con);
					nestedAggregateObj.nestedAggregateWithGroupBy(); 
					break;
				case 7:  
					Deletion deleteObj = new Deletion(con);
					deleteObj.delete(); 
					break;
				case 8:  
					Update updateObj = new Update(con);
					updateObj.update(); 
					break;
				case 9:  
				default:	quit = true;
				}

				if (!quit) {
					System.out.println("\nQuery complete; hit enter to continue.");
					in.readLine();
				}
			}

			con.close();
			in.close();
			System.out.println("\nGood Bye!\n\n");
			System.exit(0);
		}
		catch (IOException e)
		{
			System.out.println("IOException!");

			try
			{
				con.close();
				System.exit(-1);
			}
			catch (SQLException ex)
			{
				System.out.println("Message: " + ex.getMessage());
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Message: " + ex.getMessage());
		}
	}



	public static void main(String args[])
	{
		Main main = new Main();
	}
}
