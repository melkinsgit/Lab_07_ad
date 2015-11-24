package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

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

    TicketMethods ticketMethods;
    int canBeDeleted;
    String canBeDeletedStr;
    Ticket ticketToDelete;

    public DeleteByID (TicketMethods tm) {
        super("Delete By ID Option");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.ticketMethods = tm;

        if (ticketMethods.ticketQueue.size() == 0) { //no tickets!
            errorTextArea.setText("No tickets to delete!");
        }
        boolean found = false;

//        while (!found) {

            try {
                ticketMethods.printAllTickets(ticketMethods.ticketQueue, false); //display list for user
                for (Ticket ticket : ticketMethods.ticketQueue) {
                    canBeDeletedStr += ("Ticket ID: " + ticket.getTicketID() + " Issue: " + ticket.getDescription() + " Priority: " + ticket.getPriority() + " Reported by: " + ticket.getReporter() + " Reported on: " + ticket.getDateReported() + " Status: " + ticket.getStatus() + "\n");
                    canBeDeleted = ticket.getTicketID();
                    deleteIDComboBox.addItem(canBeDeleted);
                }
                deleteTextArea.setText(canBeDeletedStr);

//                Scanner deleteScanner = new Scanner(System.in);
//                System.out.println("Enter ID of ticket to delete");
//                int deleteID = ticketMethods.getTktNum(deleteScanner, deleteScanner.nextLine());

//Loop over all tickets. Delete the one with this ticket ID

//                for (Ticket ticket : ticketMethods.ticketQueue) {
//                    canBeDeleted = ticket.getTicketID();
//                    deleteIDComboBox.addItem(canBeDeleted);
//                }
//                if (found == false) {
//                    errorTextArea.setText("Ticket ID not found, no ticket deleted. Please choose another");
////                    String resp = ticketMethods.getYorN(deleteScanner.nextLine());
////                    if (resp.equalsIgnoreCase("N")) {
////                        found = true;
////                    }
//                }
            } // end try
            catch (Exception exc) {
                System.out.println("There are no tickets to delete.");
            } // end catch
//        } // end while loop
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
//                        System.out.println(String.format("Ticket %d deleted", deleteID));
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
