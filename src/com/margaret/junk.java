//package com.margaret;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Created by sn0173nd on 11/24/2015.
// */
//public class junk {
//    private JTextField creditCardNumberTextField;
//    private JButton validateButton;
//    private JButton quitButton;
//    private JPanel rootPanel;
//    private JLabel validMessageLabel;
//    private JComboBox CCtypeComboBox;
//    private boolean resetMessageOnKeyPress = false;
//
//}
//    public CCValidator() {
//        super("Credit Card Validator");
//        setPreferredSize(new Dimension(400, 300));
//        creditCardNumberTextField.setPreferredSize(new Dimension(150, 24));
//        setContentPane(rootPanel);
//        pack();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//        final String VISA = "VISA";
//        final String MC = "MasterCard";
//        final String AMEX = "AmericanExpress";
//        CCtypeComboBox.addItem(VISA);
//        CCtypeComboBox.addItem(MC);
//        CCtypeComboBox.addItem(AMEX);
//
//        validateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String ccNum = creditCardNumberTextField.getText();
//
//                // could also set the text for the validate button
//                boolean valid = isVisaValid(ccNum);
//                if (valid) {
//                    validMessageLabel.setText("You have entered a valid VISA Card number.");
//                } else {
//                    validMessageLabel.setText("Sorry, that VISA Card number is not valid.");
//                }
//                resetMessageOnKeyPress = true;
//            }
//        });
//}
//
//
//
//package com.margaret;
//
//        import javax.swing.*;
//        import java.awt.*;
//        import java.awt.event.ActionEvent;
//        import java.awt.event.ActionListener;
//
///**
// * Created by sn0173nd on 11/24/2015.
// */
//public class TicketGUI extends JFrame {
//
//    private JComboBox menuOptions;
//    private JLabel Instruct;
//    super("HVAC Menu Options");
//    setPreferredSize(new Dimension(400, 300));
//    setContentPane(rootPanel);
//    pack();
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setVisible(true);
//
//    final String enterTkt = "Enter Ticket";
//    final String deleteByID = "Delete By ID";
//    final String deleteByIss = "Delete By Issue";
//    final String searchByName = "Search By Name";
//    final String displayAll = "Display All Tickets";
//    final String quit = "Quit";
//    CCtypeComboBox.addItem(enterTkt);
//    CCtypeComboBox.addItem(deleteByID);
//    CCtypeComboBox.addItem(deleteByIss);
//
//    public TicketGUI() {
//        menuOptions.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//    }
//}