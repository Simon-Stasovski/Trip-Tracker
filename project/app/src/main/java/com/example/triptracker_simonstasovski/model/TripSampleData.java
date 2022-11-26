package com.example.triptracker_simonstasovski.model;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripSampleData {
    public static ArrayList<Trip> getData(){
        ArrayList<Trip> data = new ArrayList<>();

        try{
            data.add(new Trip(1, new Date(2022, 0, 2), new Time(1, 23, 0), 0, 50, TripTypes.Personal));
            data.add(new Trip(2, new Date(2022, 1, 3), new Time(0, 59, 0), 50, 103, TripTypes.Personal));
            data.add(new Trip(3, new Date(2022, 2, 4), new Time(1, 10, 0), 103, 141, TripTypes.Uber));
            data.add(new Trip(4, new Date(2022, 3, 5), new Time(0, 56, 0), 141, 189, TripTypes.Uber));
            data.add(new Trip(5, new Date(2022, 4, 6), new Time(1, 11, 0), 189, 212, TripTypes.Uber));
            data.add(new Trip(6, new Date(2022, 5, 7), new Time(0, 39, 0), 212, 239, TripTypes.Personal));
            data.add(new Trip(7, new Date(2022, 6, 8), new Time(1, 12, 0), 239, 256, TripTypes.Personal));
            data.add(new Trip(8, new Date(2022, 7, 9), new Time(0, 42, 0), 256, 281, TripTypes.Personal));
            data.add(new Trip(9, new Date(2022, 8, 10), new Time(1, 18, 0), 281, 301, TripTypes.Uber));
            data.add(new Trip(10, new Date(2022, 9, 11), new Time(0, 55, 0), 301, 389, TripTypes.Personal));
            data.add(new Trip(11, new Date(2022, 10, 12), new Time(1, 1, 0), 389, 415, TripTypes.Uber));
            data.add(new Trip(12, new Date(2022, 11, 13), new Time(0, 49, 0), 415, 450, TripTypes.Personal));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
