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
        //this.date=date;
    }

    //----------------------get set----------------------------------
    public String getID() {return ID;}
    public void setID(String ID) {this.ID = ID;}

    public String getTitle() {return Title;}
    public void setTitle(String title) {Title = title;}

    public String getDescription() {return Description;}
    public void setDescription(String description) {Description = description;}

    public EventType getType() {return type;}
    public void setType(EventType type) {this.type = type;}

    public String getPortfolioID() {return portfolioID;}
    public void setPortfolioID(String portfolioID) {this.portfolioID = portfolioID;}

    //public Date getDate() {return date;}
    //public void setDate(Date date) {this.date = date;}
}
