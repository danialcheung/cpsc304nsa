/*
 * Created by JFormDesigner on Sat Apr 02 18:52:40 PDT 2016
 */

package GUI;

import java.awt.*;
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

        for (int i = 0; i < Column.length-1; i++){
            table1.getColumnModel().getColumn(i).setHeaderValue(Column[i]);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - kek kek
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "54dlu, $lcgap, 82dlu, $lcgap, default",
            "7*(default, $lgap), default"));

        //---- label1 ----
        label1.setText("text");
        contentPane.add(label1, CC.xy(1, 1));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, CC.xywh(1, 5, 5, 11));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - kek kek
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
