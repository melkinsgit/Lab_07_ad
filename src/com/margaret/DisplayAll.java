package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class DisplayAll extends JFrame {
    private JTextArea errorTextArea;
    private JPanel rootPanel;
    private JButton doneButton;
    private JTextArea displayTextArea;

    TicketMethods ticketMethods;
    String toDisplay = "";

    public DisplayAll(TicketMethods tm){
        super("Display All Tickets Option");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.ticketMethods = tm;

        if (ticketMethods.ticketQueue.size() == 0) { //no tickets!
            errorTextArea.setText("No tickets to display!");
        }

        for (Ticket ticket : ticketMethods.ticketQueue){
            toDisplay += ("Ticket ID: " + ticket.getTicketID() + " Issue: " + ticket.getDescription() + " Priority: " + ticket.getPriority() + " Reported by: " + ticket.getReporter() + " Reported on: " + ticket.getDateReported() + " Status: " + ticket.getStatus() + "\n");
        }
        System.out.println("all tickets are: " + toDisplay);
        displayTextArea.setText(toDisplay);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
