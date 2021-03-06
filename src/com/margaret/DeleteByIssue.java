package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class DeleteByIssue extends JFrame {


    private JLabel instructLabel;
    private JTextField issueTextField;
    private JPanel rootPanel;
    private JTextArea errorTextArea;
    private JLabel deleteOptionsLabel;
    private JComboBox deleteIssueComboBox;
    private JLabel deleteChoiceLabel;
    private JTextField resolutionTextField;
    private JLabel resolutionLabel;
    private JTextArea deleteOptionsTextArea;

    private TicketMethods ticketMethods;
    private String issueText;
    private LinkedList<Ticket> matching;
    private int canBeDeleted;
    private String canBeDeletedStr = "";
    private Ticket ticketToDelete;

    public DeleteByIssue (TicketMethods tm){
        super("Delete By Issue");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.ticketMethods = tm;

        if (ticketMethods.ticketQueue.size() == 0) { //no tickets!
            errorTextArea.setText("No tickets to delete!");
        }

        issueTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketMethods.deleteIssue = issueTextField.getText();
                boolean found = false;
                while (!found) {

                    try {
                        //Call method to create a Linked List of matching Tickets
                        matching = ticketMethods.listOfMatches(ticketMethods.ticketQueue, ticketMethods.deleteIssue);
                        found = true;
                    } // end try

                    catch (Exception exc) {
                        errorTextArea.setText("Sorry, that's not a valid. Please enter a phrase or string of letters.");
                    }
                }   // end while loop

                for (Ticket ticket : matching) {
                    canBeDeletedStr += ("Ticket ID: " + ticket.getTicketID() + " Issue: " + ticket.getDescription() + " Priority: " + ticket.getPriority() + " Reported by: " + ticket.getReporter() + " Reported on: " + ticket.getDateReported() + " Status: " + ticket.getStatus() + "\n");
                    canBeDeleted = ticket.getTicketID();
                    deleteIssueComboBox.addItem(canBeDeleted);
                }
                deleteOptionsTextArea.setText(canBeDeletedStr);
            }
        });
        deleteIssueComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketMethods.deleteID = (int) deleteIssueComboBox.getSelectedItem();
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
