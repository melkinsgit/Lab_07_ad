package com.margaret;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private int priority;
    private String reporter; //Stores person or department who reported issue
    private String description;
    private Date dateReported;
    private String resolution;
    private Date dateResolved;
    private String status;  // value can only be open or closed
    //STATIC Counter - accessible to all Ticket objects.
//If any Ticket object modifies this counter, all Ticket objects will have the modified value
//Make it private - only Ticket objects should have access
    private static int staticTicketIDCounter = 1;
    //The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
    protected int ticketID;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    // constructor
    public Ticket(String desc, int p, String rep, Date dateRep) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = dateRep;
        this.status = "open";
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    public Ticket(String[] params) {
        this.description = params[0];
        this.priority = Integer.parseInt(params[1]);
        this.reporter = params[2];
        this.dateReported = params[3];
        this.status = params[4];
        this.resolution = params[6];
        this.dateResolved = params[7];
        staticTicketIDCounter =
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    public Date getDateReported() { return dateReported; }

    public String getResolution() { return resolution; }

    public Date getDateResolved() { return dateResolved; }

    public String getReporter() { return reporter; }

    public int getTicketID() {
        return ticketID;
    }

    public String getDescription() {
        return description;
    }

    protected int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String toString(){
        String addon = "";
        if (this.status.equals("closed")){
            addon = " Date Resolved: " + this.getDateResolved() + " Resolution: " + this.getResolution();
        }
        return("ID: " + this.ticketID + " Issued: " + this.description + " Priority: " + this.priority + " Reported by: " + this.reporter + " Reported on: " + this.dateReported) + " Status: " + this.getStatus() + addon;
    }
}