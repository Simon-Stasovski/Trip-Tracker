package com.example.triptracker_simonstasovski;

import com.example.triptracker_simonstasovski.model.Trip;
import com.example.triptracker_simonstasovski.model.TripSampleData;
import com.google.type.DateTime;

import java.util.ArrayList;

public class TripRepositoryImpl implements TripRepository{

    public static ArrayList<Trip> trips = new ArrayList<>(TripSampleData.getData());

    @Override
    public ArrayList<Trip> get() {
        return this.trips;
    }

    @Override
    public Trip getById(int id) {
        return trips.get(id - 1);
    }

    @Override
    public ArrayList<Trip> getByDate(DateTime date) {

        ArrayList<Trip> filteredList = new ArrayList<>();

        for (Trip trip: trips) {
            if(trip.getDateOfTrip().getYear() == date.getYear() &&
                    trip.getDateOfTrip().getMonth() == date.getMonth() &&
                    trip.getDateOfTrip().getDay() == date.getDay()){
                filteredList.add(trip);
            }
        }
        return filteredList;
    }

    @Override
    public void Add(Trip trip) {
        this.trips.add(trip);
    }

    @Override
    public Trip update(Trip trip) {
        for (Trip t: trips) {
            if(t.getId() == trip.getId()){
                t.setDateOfTrip(trip.getDateOfTrip());
                t.setTripStartTime(trip.getTripStartTime());
                t.setTripType(trip.getTripType());
                t.setStartOdometer(trip.getStartOdometer());
                t.setEndOdometer(trip.getEndOdometer());
                return t;
            }
        }
        return null;
    }

    @Override
    public void delete(Trip trip) {
        this.trips.remove(trip);
    }
}
