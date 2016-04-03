/*
 * Created by JFormDesigner on Sat Apr 02 18:52:40 PDT 2016
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author kek kek
 */
public class TableWindow extends JFrame {
    public TableWindow() {
        initComponents();
    }

    public void initializeTables(String[] Column){
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < Column.length; i++){
            model.addColumn(Column[i]);
        }
        table1.setModel(model);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - kek kek
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "54dlu:grow, $lcgap, 82dlu, $lcgap, default",
            "10*(default, $lgap), default"));

        //---- label1 ----
        label1.setText("Test");
        contentPane.add(label1, CC.xy(1, 1));

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 5, 5, 11));

        //---- button1 ----
        button1.setText("Populate!");
        contentPane.add(button1, CC.xy(3, 21));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - kek kek
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
