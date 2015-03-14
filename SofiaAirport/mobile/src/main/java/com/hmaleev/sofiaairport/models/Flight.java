package com.hmaleev.sofiaairport.models;

import java.io.Serializable;

public class Flight implements Serializable {
    private String ScheduledDate;
    private String ScheduledTime;
    private String FlightNo;
    private String PlaneType;
    private String ArrivesFrom;
    private String DepartsFor;
    private String Terminal;
    private String ExpectedTime;
    private String Status;
    private String GroundOperator;
    private String MoreDetails;

    public String getScheduledDate(){
        return this.ScheduledDate;
    }
    public void setScheduledDate (String scheduledDate){
        this.ScheduledDate = scheduledDate;
    }

    public String getScheduledTime(){
        return this.ScheduledTime;
    }
    public void setScheduledTime (String scheduledTime){
        this.ScheduledTime = scheduledTime;
    }

    public String getFlightNo(){
        return this.FlightNo;
    }
    public void setFlightNo (String flightNo){
        this.FlightNo = flightNo;
    }

    public String getPlaneType (){
        return this.PlaneType;
    }
    public  void setPlaneType (String planeType){
        this.PlaneType = planeType;
    }

    public String getArrivesFrom (){
        return this.ArrivesFrom;
    }
    public  void setArrivesFrom (String arrivesFrom){
        this.ArrivesFrom = arrivesFrom;
    }

    public String getDepartsFor (){
        return this.DepartsFor;
    }
    public  void setDepartsFor (String departsFor){
        this.DepartsFor = departsFor;
    }

    public String getTerminal (){
        return this.Terminal;
    }
    public  void setTerminal (String terminal){
        this.Terminal = terminal;
    }

    public String getExpectedTime (){
        return this.ExpectedTime;
    }
    public  void setExpectedTime (String expectedTime){
        this.ExpectedTime = expectedTime;
    }

    public String getStatus (){
        return this.Status;
    }
    public  void setStatus(String status){
        this.Status = status;
    }

    public String getGroundOperator (){
        return this.GroundOperator;
    }
    public  void setGroundOperator (String groundOperator){
        this.GroundOperator = groundOperator;
    }

    public String getMoreDetails (){
        return this.MoreDetails;
    }
    public  void setMoreDetails(String moreDetails){
        this.MoreDetails = moreDetails;
    }

}
