package org.isep.financialproject;

public class Event {
    private String ID;
    private String Title;
    private String Description;
    //private Date date;
    private EventType type;
    private String portfolioID;

    public Event(String ID,String Title,String Description,
                 EventType type,String portfolioID){
        this.ID=ID;
        this.Title=Title;
        this.Description=Description;
        this.type=type;
        this.portfolioID=portfolioID;


    }


}
