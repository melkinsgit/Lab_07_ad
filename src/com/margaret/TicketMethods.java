package com.margaret;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by sn0173nd on 11/24/2015.
 */
public class TicketMethods {

    protected LinkedList<Ticket> ticketQueue = new LinkedList<>(); // changed to protected, no longer static
    protected LinkedList<Ticket> resolvedTickets = new LinkedList<>(); // changed to protected, no longer static
    protected int inputPriority;
    protected String inputDescr;
    protected String inputReporter;
    protected int deleteID;
    protected String deleteIssue;

    public TicketMethods() {
        TicketGUI ticketGUI = new TicketGUI(this);
    }

    // previsously all METHODS were static as part of main; no longer now as part of TicketMethods class
    //METHOD FILE IO______________________________________________________________________________
    protected void fileIO (){
        int lastCounter;
        try {   // TODO manage situation where OpenTkts.txt does not yet exist
            FileReader fRead = new FileReader("OpenTkts.txt");
            BufferedReader bRead = new BufferedReader(fRead);
            String ticketLine = bRead.readLine();
//            System.out.println(ticketLine);
            lastCounter = Integer.parseInt(ticketLine);
//            System.out.println(lastCounter);
            Ticket.staticTicketIDCounter = lastCounter;
            ticketLine = bRead.readLine();
            int ticketID;
            String description;
            int priority;
            String reporter;
            Date dateReported;  // TODO make date import from OpenTkts file work
            String status;
            Date dateResolved;  // TODO make date import from OpenTkts file work
            String resolution;
            while (ticketLine != (null)){
//                System.out.println(ticketLine);
                String [] params = ticketLine.split("\t");
                ticketID = Integer.parseInt(params[0]);
                if (ticketID > Ticket.staticTicketIDCounter){
                    Ticket.staticTicketIDCounter = ticketID;
                }
                description = params[1];
                priority = Integer.parseInt(params[2]);
                reporter = params[3];
                dateReported = new Date();  // TODO make date import from OpenTkts file work
                status = params[5];
                dateResolved = new Date();  // TODO make date import from OpenTkts file work
                resolution = params[7];
                Ticket t = new Ticket(ticketID, description, priority, reporter, status, resolution);
                addTicketInPriorityOrder(ticketQueue, t);
                ticketLine = bRead.readLine();
            }
        }
        catch (IOException io){
            System.out.println("Problem with input file.");
        }
    }

    //METHOD OUTPUT TO FILE_________________________________________________________________
    protected void outputToFile(String fName) {
        try{
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("MM_dd_yyyy");
            String nowStr = df.format(now);
            LinkedList<Ticket> listToUse;
            String fTitle = "";
            if (fName.equals("Resolved")) {
                fTitle = fName + "Tkts_as_of_" + nowStr + ".txt";
                listToUse = resolvedTickets;
            }
            else {
                fTitle = fName + "Tkts.txt";
                listToUse = ticketQueue;
            }
            FileWriter w = new FileWriter(fTitle);
            BufferedWriter bw = new BufferedWriter(w);
            if (fName.equals("Open")){
                bw.write(Ticket.staticTicketIDCounter + "");
            }
            String writeStr = "";
            for (Ticket t : listToUse) {
                writeStr = ("\n" + t.getTicketID() + "\t" + t.getDescription() + "\t" + t.getPriority() + "\t" + t.getReporter() + "\t" + t.getDateReported()) + "\t" + t.getStatus() + "\t" + t.getDateResolved() + "\t" + t.getResolution();
                bw.write(writeStr);
            }
            bw.close();
        }
        catch (IOException write){
            System.out.println("There was a problem with one of the output files.");
        }
    }

    // METHOD GET NUM CHOICE______________________________________________________________________
    protected int getNumChoice(Scanner scan, String menu, int i) {
        int choice = 0;
        boolean valid = false;
        String s = scan.nextLine();
        while (!valid){
            try {
                choice = Integer.parseInt(s);
                if (choice >= 1 && choice <= i) {
                    valid = true;
                }
                else{
                    System.out.println("Sorry, that's answer won't work. Please enter a number between 1 and " + i + ".");
                    System.out.println(menu);
                    s = scan.nextLine();
                }
            } catch (Exception ex) {
                System.out.println("Sorry, that's answer won't work. Please enter a number between 1 and " + i + ".");
                System.out.println(menu);
                s = scan.nextLine();
            }
        }
        return choice;
    }

    // METHOD SEARCH BY NAME______________________________________________________________________
    protected void searchByName() {

        if (ticketQueue.size() == 0) { //no tickets!
            System.out.println("No tickets to search!\n");
            return;
        }
        boolean found = false;

        while (!found) {

            try {
                Scanner deleteScanner = new Scanner(System.in);
                System.out.println("Enter an issue");
                String deleteIssue = deleteScanner.nextLine();

//Call method to create a Linked List of matching Tickets
                LinkedList<Ticket> matching = listOfMatches (ticketQueue, deleteIssue);
                if (matching.size() > 0) {
                    found = true;
                    deleteByID(matching);
                }
                else {
                    System.out.println("Ticket Issue not found. Would you like to try again? (Y or N)");
                    String resp = getYorN(deleteScanner.nextLine());
                    if (resp.equalsIgnoreCase("N")) {
                        found = true;
                    }
                }
            } // end try

            catch (Exception exc){
                System.out.println("Please enter a response in words.");
                System.out.println(exc);
            } // end catch
        } // end while loop
    } // end deleteTicket fn

    // METHOD DELETE BY ID__________________________________________________________________________
    protected void deleteByID(LinkedList<Ticket> ticketQueue) {

        if (ticketQueue.size() == 0) { //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        }
        boolean found = false;

        while (!found) {

            try {
                printAllTickets(ticketQueue, false); //display list for user
                Scanner deleteScanner = new Scanner(System.in);
                System.out.println("Enter ID of ticket to delete");
                int deleteID = getTktNum(deleteScanner, deleteScanner.nextLine());

//Loop over all tickets. Delete the one with this ticket ID

                for (Ticket ticket : ticketQueue) {
                    if (ticket.getTicketID() == deleteID) {
                        found = true;
                        resolveTicket(ticket);
                        ticketQueue.remove(ticket);
                        System.out.println(String.format("Ticket %d deleted", deleteID));
                        break; //don't need loop any more.
                    }
                }
                if (found == false) {
                    System.out.println("Ticket ID not found, no ticket deleted. Would you like to try again? (Y or N)");
                    String resp = getYorN(deleteScanner.nextLine());
                    if (resp.equalsIgnoreCase("N")) {
                        found = true;
                    }
                }
            } // end try
            catch (Exception exc){
                System.out.println("Please enter a whole number without decimals.");
            } // end catch
        } // end while loop
    } // end deleteTicket fn

    // METHOD GET Y OR N______________________________________________________________________
    protected String getYorN(String s) {
        Scanner scan = new Scanner(System.in);
        String choice = "";
        boolean valid = false;
        while (!valid){
            if (s.equals("n") || s.equals("N") || s.equals("y") || s.equals("Y")) {
                choice = s;
                valid = true;
            }
            else{
                System.out.println("Sorry, that's not a valid response. Please enter Y or N.");
                s = scan.nextLine();
            }
        }
        return choice;
    }

    // METHOD GET TKT NUM______________________________________________________________________
    protected int getTktNum(Scanner numScan, String s) {
        boolean valid = false;
        int sNum = 0;
        while (!valid) {
            try {
                sNum = Integer.parseInt(s);
                if (sNum < 0){
                    System.out.println("Sorry, your number entry has to be greater than 0.");
                    s = numScan.nextLine();
                }
                else {valid = true;}
            } catch (Exception ex) {
                System.out.println("Sorry, that entry isn't valid. Please enter a whole number greater than 0.");
                s = numScan.nextLine();
            }
        }
        return sNum;
    }

    // METHOD RESOLVE TICKET_______________________________________________________________________
    protected void resolveTicket(Ticket ticket) {
        ticket.setDateResolved(new Date());
        ticket.setStatus("closed");
        resolvedTickets.add(ticket);
    } // end resolveTicket fn

    // METHOD DELETE BY ISSUE______________________________________________________________________
//    protected void deleteByIssue() {
//
//        if (ticketQueue.size() == 0) { //no tickets!
//            System.out.println("No tickets to delete!\n");
//            return;
//        }
//        boolean found = false;
//
//        while (!found) {
//
//            try {
//                Scanner deleteScanner = new Scanner(System.in);
//                System.out.println("Enter an issue");
//                String deleteIssue = deleteScanner.nextLine();
//
////Call method to create a Linked List of matching Tickets
//
//                LinkedList<Ticket> matching = listOfMatches(ticketQueue, deleteIssue);
//                deleteByID(matching);
//                found = true;
//            } // end try
//
//            catch (Exception exc){
//                System.out.println("Sorry, that's not a valid. Please enter a phrase or string of letters.");
//            }
//        }   // end while loop
//    } // end deleteTicket fn

    // METHOD LIST OF MATCHES_________________________________________________________________
    protected LinkedList<Ticket> listOfMatches(LinkedList<Ticket> ticketQueue, String deleteIssue) {

        LinkedList<Ticket> result = new LinkedList<>();

        for (Ticket ticket : ticketQueue) {
            if (ticket.getDescription().contains(deleteIssue)) {
                result.add(ticket);
            }
        }
        return result;
    }

    // METHOD ADD TICKETS_________________________________________________________________
    //Move the adding ticket code to a method
    protected void addTickets() {
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        Ticket t = new Ticket(inputDescr, inputPriority, inputReporter, dateReported);
        addTicketInPriorityOrder(ticketQueue, t);
        //To test, let's print out all of the currently stored tickets
        printAllTickets(ticketQueue, false);
    }

    // METHOD PRINT ALL TICKETS__________________________________________________________
    protected void printAllTickets(LinkedList<Ticket> tickets, Boolean resolved) {
        if (resolved){
            System.out.println(" ------- Resolved tickets ----------");
            for (Ticket t : tickets ) {
                System.out.println(t); //Write a toString method in Ticket class
//println will try to call toString on its argument
            }
        }
        else {
            System.out.println(" ------- All open tickets ----------");
            for (Ticket t : tickets) {
                System.out.println(t); //Write a toString method in Ticket class
//println will try to call toString on its argument
            }
        }
        System.out.println(" ------- End of ticket list --------");
    }


    // METHOD ADD TICKET IN PRIORITY ORDER___________________________________________________
    protected void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket){
//Logic: assume the list is either empty or sorted
        if (tickets.size() == 0 ) {//Special case - if list is empty, add ticket and return
            tickets.add(newTicket);
            return;
        }
//Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
//Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end
        int newTicketPriority = newTicket.getPriority();
        for (int x = 0; x < tickets.size() ; x++) { //use a regular for loop so we know which element we are looking at
//if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority >= tickets.get(x).getPriority()) {
                tickets.add(x, newTicket);
                return;
            }
        }
//Will only get here if the ticket is not added in the loop
//If that happens, it must be lower priority than all other tickets. So, add to the end.
        tickets.addLast(newTicket);
    }
}
