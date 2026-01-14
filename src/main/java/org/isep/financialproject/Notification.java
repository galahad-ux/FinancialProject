package org.isep.financialproject;

public class Notification {
    //private String ID;
    //private String Title;
    //private String Description;
    //private Date date;
    private NotificationType type;
    private String portfolioID;
    private String message;

    public Notification(NotificationType type, String portfolioID,String message){


        this.message=message;
        this.type=type;
        this.portfolioID=portfolioID;
        //this.date=date;
    }

    //----------------------get set----------------------------------
    //public String getID() {return ID;}
    //public void setID(String ID) {this.ID = ID;}

    //public String getTitle() {return Title;}
    //public void setTitle(String title) {Title = title;}


    public NotificationType getType() {return type;}
    public void setType(NotificationType type) {this.type = type;}

    public String getPortfolioID() {return portfolioID;}
    public void setPortfolioID(String portfolioID) {this.portfolioID = portfolioID;}

    //public Date getDate() {return date;}
    //public void setDate(Date date) {this.date = date;}

    public void setMessage(String message) {message = message;}


    public String getMessage() {
        return message;
    }

}
