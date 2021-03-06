package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class TicketGUI extends JFrame {
    private JPanel rootPanel;
    private JLabel Instruct;
    private JComboBox menuOptions;

    public TicketGUI (TicketMethods ticketMethods) {
        super("HVAC Menu Options");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        final String startStr = "";
        final String enterTkt = "Enter Ticket";
        final String deleteByID = "Delete By ID";
        final String deleteByIss = "Delete By Issue";
        final String searchByName = "Search By Issue";
        final String displayAll = "Display All Tickets";
        final String quit = "Quit";
        menuOptions.addItem(startStr);
        menuOptions.addItem(enterTkt);
        menuOptions.addItem(deleteByID);
        menuOptions.addItem(deleteByIss);
        menuOptions.addItem(searchByName);
        menuOptions.addItem(displayAll);
        menuOptions.addItem(quit);

        menuOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuOptions.getSelectedItem().equals(enterTkt)) {
                     AddTicket addTicket = new AddTicket(ticketMethods);
                }
                if (menuOptions.getSelectedItem().equals(deleteByID)) {
                    DeleteByID deleteByID = new DeleteByID(ticketMethods);
                }
                if (menuOptions.getSelectedItem().equals(deleteByIss)) {
                    DeleteByIssue deleteByIssue = new DeleteByIssue(ticketMethods);
                }
                if (menuOptions.getSelectedItem().equals(searchByName)) {
                    SearchByName searchByName = new SearchByName(ticketMethods);
                }
                if (menuOptions.getSelectedItem().equals(displayAll)) {
                    DisplayAll displayAll = new DisplayAll(ticketMethods);
                }
                if (menuOptions.getSelectedItem().equals(quit)){
                    ticketMethods.outputToFile("Resolved");
                    ticketMethods.outputToFile("Open");
                    System.exit(0);
                }
                menuOptions.setSelectedIndex(0);
            }
        });
    }
}
