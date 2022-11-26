package com.example.triptracker_simonstasovski;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triptracker_simonstasovski.model.Trip;
import com.example.triptracker_simonstasovski.model.TripTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.ViewHolder> implements Filterable {

    private ArrayList<Trip> mTrips;
    private ArrayList<Trip> listForFiltering;
    private RecyclerView adapter;

    public RecyclerView getAdapter() {
        return adapter;
    }

    public TripRecyclerViewAdapter(ArrayList<Trip> trips){
        this.mTrips = trips;
        listForFiltering = new ArrayList<>(trips);
    }

    @NonNull
    @Override
    public TripRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trip, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mTrips.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    @Override
    public Filter getFilter() {
        return dateFilter;
    }

    private final Filter dateFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Trip> filteredList = new ArrayList<>();
            listForFiltering = new ArrayList<>(MainActivity.tripRepository.get());

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listForFiltering);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Trip trip : listForFiltering) {
                    if (trip.getDateOfTripString().contains(filterPattern)) {
                        filteredList.add(trip);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mTrips = (ArrayList<Trip>) results.values;
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tripDate, tripLength, odometerStart, odometerEnd, tripType;
        private Button modifyButton;
        private View tripLayout;
        private Trip mtrip;
        private int position;


        public ViewHolder(final View view) {
            super(view);
            tripDate = view.findViewById(R.id.tripDateItem_TextView);
            tripLength = view.findViewById(R.id.tripTimeItem_TextView);
            odometerStart = view.findViewById(R.id.odometerStartItem_TextView);
            odometerEnd = view.findViewById(R.id.odometerEndItem_TextView);
            tripType = view.findViewById(R.id.tripTypeItem_TextView);
            tripLayout = view.findViewById(R.id.trip_constraintLayout);

            modifyButton = view.findViewById(R.id.modifyButton);

            modifyButton.setOnClickListener(v -> {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentChanger_ConstraitLayout, new EditDeleteItemFragment(mtrip))
                        .commit();
            });
        }
        public void bind(Trip trip, int position){
            this.mtrip = trip;
            this.position = position;

            tripDate.setText(trip.getDateOfTripString());
            tripLength.setText(trip.getTripStartTime().toString());
            odometerStart.setText(Integer.toString(trip.getStartOdometer()));
            odometerEnd.setText(Integer.toString(trip.getEndOdometer()));
            tripType.setText(trip.getTripTypeString());
            tripLayout.setBackgroundColor(trip.getTripType() == TripTypes.Personal ? Color.parseColor("#aba791") : Color.parseColor("#426ff5"));
        }
    }
}
