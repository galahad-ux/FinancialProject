package org.isep.financialproject;
import  java.time.LocalDateTime;

public class Notification {
    private LocalDateTime dateTime;
    private NotificationType type;
    private String portfolioID;
    private String message;

    public Notification(LocalDateTime dateTime,NotificationType type, String portfolioID,String message){
        this.dateTime =dateTime;
        this.message=message;
        this.type=type;
        this.portfolioID=portfolioID;
    }

    //----------------------get set----------------------------------
    public LocalDateTime getDateTime() {return dateTime;}
    public void setDateTime(LocalDateTime dateTime) {this.dateTime = dateTime;}

    public NotificationType getType() {return type;}
    public void setType(NotificationType type) {this.type = type;}

    public String getPortfolioID() {return portfolioID;}
    public void setPortfolioID(String portfolioID) {this.portfolioID = portfolioID;}

    public void setMessage(String message) {message = message;}

    public String getMessage() {
        return message;
    }

}
