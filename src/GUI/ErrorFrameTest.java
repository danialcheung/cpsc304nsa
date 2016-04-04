package GUI;

import javax.swing.*;
import java.awt.event.*;

public class ErrorFrameTest {

	public ErrorFrameTest() {
		JFrame f = new JFrame("Error");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		f.setSize(400,300);
		f.setLocationByPlatform(true);
		f.setVisible(true);

		JDialog d = new JDialog(f);
		d.setTitle("Error");
		d.setSize(300,200);
		d.setLocationRelativeTo(f);
		JButton button = new JButton("OK");
	    button.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        d.dispose();
	      }
	    });
	    d.add(button);
		d.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ErrorFrameTest();
            }
        });
    }

}
