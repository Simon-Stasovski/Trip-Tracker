package com.example.triptracker_simonstasovski;

import com.example.triptracker_simonstasovski.model.Trip;
import com.google.type.DateTime;

import java.util.ArrayList;

public interface TripRepository {
    ArrayList<Trip> get();
    Trip getById(int id);
    ArrayList<Trip> getByDate(DateTime date);
    void Add(Trip trip);
    Trip update(Trip trip);
    void delete(Trip trip);
}
