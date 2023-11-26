import history.Memento;
import history.SimHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseMementoWindow extends JFrame {
    private SimHistory simHistory;
    private JList<String> mementoList;

    private ActionListener selectListener; // Listener for the selection


    public ChooseMementoWindow(SimHistory simHistory) {
        this.simHistory = simHistory;

        // Create a list model with memento indices as strings
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < simHistory.getHistorySize(); i++) {
            listModel.addElement("Save no. " + i);
        }

        // Create JList with the list model
        mementoList = new JList<>(listModel);

        // Create a button to select the chosen memento
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = mementoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Memento selectedMemento = simHistory.get(selectedIndex);
                    // Do something with the selected memento
                    System.out.println("Selected Memento: " + selectedMemento);

                    // Notify the listener that a selection has been made
                    if (selectListener != null) {
                        selectListener.actionPerformed(e);
                    }
                } else {
                    JOptionPane.showMessageDialog(ChooseMementoWindow.this,
                            "Please select a memento from the list.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set up the layout
        setLayout(new BorderLayout());
        add(new JScrollPane(mementoList), BorderLayout.CENTER);
        add(selectButton, BorderLayout.SOUTH);

        // Set frame properties
        setTitle("Choose Memento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    public void setSelectListener(ActionListener listener) {
        this.selectListener = listener;
    }

    public int getSelectedIndex() {
        return mementoList.getSelectedIndex();
    }


}
