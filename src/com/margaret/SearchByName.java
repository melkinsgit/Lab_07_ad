package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class SearchByName extends JFrame {

    private TicketMethods ticketMethods;
    private JTextField issueToSearchTextField;
    private JLabel searchNameLabel;
    private JPanel rootPanel;
    private JTextArea errorTextArea;
    private JTextArea displayTextArea;
    private JLabel displayLabel;
    private JButton doneButton;
    private String toDisplay = "";

    LinkedList<Ticket> matching;

    public SearchByName (TicketMethods tm){
        super("Search by Issue and Display");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.ticketMethods = tm;
        if (ticketMethods.ticketQueue.size() == 0) { //no tickets!
            errorTextArea.setText("No tickets to search!");
        }
        issueToSearchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //        LinkedList<Ticket> matching = listOfMatches (ticketQueue, deleteIssue);
                ticketMethods.deleteIssue = issueToSearchTextField.getText();
                matching = ticketMethods.listOfMatches(ticketMethods.ticketQueue, ticketMethods.deleteIssue);

                if (matching.size() > 0) {
                    for (Ticket ticket : matching) {
                        toDisplay += ("Ticket ID: " + ticket.getTicketID() + " Issue: " + ticket.getDescription() + " Priority: " + ticket.getPriority() + " Reported by: " + ticket.getReporter() + " Reported on: " + ticket.getDateReported() + " Status: " + ticket.getStatus() + "\n");
                    }
                    displayTextArea.setText(toDisplay);
                }

                else {
                    errorTextArea.setText("No tickets match that search.");
                }
            }
        });
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}