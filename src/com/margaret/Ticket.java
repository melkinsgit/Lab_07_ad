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
    protected static int staticTicketIDCounter = 1;
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

    public void setStaticCounter (int num){
        staticTicketIDCounter = num;
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

    public Ticket(int ID, String descr, int prior, String report, String stat, String reso) {
        this.ticketID = ID;
        this.description = descr;
        this.priority = prior;
        this.reporter = report;
        this.dateReported = new Date();  // TODO make date import from OpenTkts file work
        this.status = stat;
        this.dateResolved = new Date();  // TODO make date import from OpenTkts file work
        this.resolution = reso;
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