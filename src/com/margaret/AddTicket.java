package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class AddTicket extends JFrame {
    private JTextField reporterTextField;
    private JPanel rootPanel;
    private JLabel problem;
    private JLabel reporter;
    private JComboBox priorComboBox;
    private JLabel priority;
    private JButton enterTicketButton;
    private JTextField problemTextField;

    String inputPriority = "";
    TicketMethods ticketMethods;

    public AddTicket(TicketMethods tm) {
        super("Add Ticket Option");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.ticketMethods = tm;

        final int start = 0;
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        priorComboBox.addItem(start);
        priorComboBox.addItem(one);
        priorComboBox.addItem(two);
        priorComboBox.addItem(three);
        priorComboBox.addItem(four);
        priorComboBox.addItem(five);


        priorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketMethods.inputPriority = (int) priorComboBox.getSelectedItem();
            }
        });
        enterTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketMethods.inputDescr = problemTextField.getText();
                ticketMethods.inputReporter = reporterTextField.getText();
                setVisible(false);
                ticketMethods.addTickets();
            }
        });
        problemTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        reporterTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
