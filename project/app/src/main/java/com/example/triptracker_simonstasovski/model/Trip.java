package com.example.triptracker_simonstasovski.model;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip{
    private int id;
    private Date dateOfTrip;
    private Time tripStartTime;
    private int startOdometer;
    private int endOdometer;
    private TripTypes tripType;

    public Trip(int id, Date dateOfTrip, Time tripLength, int startOdometer, int endOdometer, TripTypes tripType){
        this.id = id;
        dateOfTrip.setYear(dateOfTrip.getYear() - 1900);
        this.dateOfTrip = dateOfTrip;
        this.tripStartTime = tripLength;
        this.startOdometer = startOdometer;
        this.endOdometer = endOdometer;
        this.tripType = tripType;
    }

    public int getId(){
        return id;
    }

    public Date getDateOfTrip(){
        return this.dateOfTrip;
    }
    public String getDateOfTripString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(this.dateOfTrip);
    }

    public void setDateOfTrip(Date dateOfTrip) {
        this.dateOfTrip = dateOfTrip;
    }

    public Time getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(Time tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public int getStartOdometer() {
        return startOdometer;
    }

    public int getEndOdometer() {
        return endOdometer;
    }

    public void setStartOdometer(int startOdometer) {
        this.startOdometer = startOdometer;
    }
    public void setEndOdometer(int endOdometer) {
        this.endOdometer = endOdometer;
    }
    public String getTripTypeString() {
        return tripType.toString();
    }
    public TripTypes getTripType(){
        return tripType;
    }

    public void setTripType(TripTypes tripType) {
        this.tripType = tripType;
    }
}
