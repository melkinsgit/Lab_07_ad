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
        this.fileIO();
    }

    // previsously all METHODS were static as part of main; no longer now as part of TicketMethods class
    //METHOD FILE IO______________________________________________________________________________
    protected void fileIO (){
        int lastCounter;
        try {   // TODO manage situation where OpenTkts.txt does not yet exist
            FileReader fRead = new FileReader("OpenTkts.txt");
            BufferedReader bRead = new BufferedReader(fRead);
            String ticketLine = bRead.readLine();
            lastCounter = Integer.parseInt(ticketLine);
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

    // METHOD RESOLVE TICKET_______________________________________________________________________
    protected void resolveTicket(Ticket ticket) {
        ticket.setDateResolved(new Date());
        ticket.setStatus("closed");
        resolvedTickets.add(ticket);
    } // end resolveTicket fn


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
//        printAllTickets(ticketQueue, false);
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
