package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class DeleteByID extends JFrame {
    private JPanel rootPanel;
    private JLabel deleteIDLabel;
    private JComboBox deleteIDComboBox;
    private JTextArea deleteTextArea;
    private JLabel toDeleteLabel;
    private JTextArea errorTextArea;
    private JLabel resolutionLabel;
    private JTextField resolutionTextField;

    private TicketMethods ticketMethods;
    private int canBeDeleted;
    private String canBeDeletedStr;
    private Ticket ticketToDelete;

    public DeleteByID (TicketMethods tm) {
        super("Delete By ID");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.ticketMethods = tm;

        if (ticketMethods.ticketQueue.size() == 0) { //no tickets!
            errorTextArea.setText("No tickets to delete!");
        }

        try {
//            ticketMethods.printAllTickets(ticketMethods.ticketQueue, false); //display list for user
            for (Ticket ticket : ticketMethods.ticketQueue) {
                canBeDeletedStr += ("Ticket ID: " + ticket.getTicketID() + " Issue: " + ticket.getDescription() + " Priority: " + ticket.getPriority() + " Reported by: " + ticket.getReporter() + " Reported on: " + ticket.getDateReported() + " Status: " + ticket.getStatus() + "\n");
                canBeDeleted = ticket.getTicketID();
                deleteIDComboBox.addItem(canBeDeleted);
            }
            deleteTextArea.setText(canBeDeletedStr);
            } // end try
            catch (Exception exc) {
                errorTextArea.setText("There are no tickets to delete.");
            } // end catch

        deleteIDComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketMethods.deleteID = (int)deleteIDComboBox.getSelectedItem();
                boolean found1 = false;
                while (!found1) {
                    for (Ticket ticket : ticketMethods.ticketQueue) {
                        if (ticket.getTicketID() == ticketMethods.deleteID) {
                            found1 = true;
                            ticketToDelete = ticket;
                            break; //don't need loop any more.
                        }
                    }
                }

            }
        });
       resolutionTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketToDelete.setResolution(resolutionTextField.getText());
                setVisible(false);
                ticketMethods.resolveTicket(ticketToDelete);
                ticketMethods.ticketQueue.remove(ticketToDelete);
            }
        });
    }
}
