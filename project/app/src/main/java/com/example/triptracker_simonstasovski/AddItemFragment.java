package com.example.triptracker_simonstasovski;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.triptracker_simonstasovski.databinding.FragmentAddItemBinding;
import com.example.triptracker_simonstasovski.model.Trip;
import com.example.triptracker_simonstasovski.model.TripTypes;

import java.sql.Time;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment {

    private  FragmentAddItemBinding binding;
    static int lastId = MainActivity.tripRepository.trips.get(MainActivity.tripRepository.trips.size() - 1).getId();
    private Button addButton;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int startOdometer, endOdometer;

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddItemBinding.inflate(inflater, container, false);

        addButton = binding.addButton;
        datePicker = binding.newTripDatePicker;
        timePicker = binding.newTripTimePicker;

        addButton.setOnClickListener(v -> {
            lastId++;

            startOdometer = Integer.parseInt(binding.startOdometerEditText.getText().toString());
            endOdometer = Integer.parseInt(binding.endOdometerEditText.getText().toString());

            if(startOdometer > endOdometer){
                Toast.makeText(binding.getRoot().getContext(),"End odometer can no-t be smaller than start odometer.", Toast.LENGTH_LONG).show();
                return;
            }

            int radioButtonID = binding.tripTypeRadioGroup.getCheckedRadioButtonId();

            RadioButton radioButton = (RadioButton) binding.tripTypeRadioGroup.findViewById(radioButtonID);

            String selectedText = (String) radioButton.getText();

            TripTypes tripType = selectedText.equals("Uber") ? TripTypes.Uber : TripTypes.Personal;

            MainActivity.tripRepository.Add(new Trip(lastId, new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()),
                    new Time(timePicker.getHour(), timePicker.getMinute(), 0), startOdometer, endOdometer, tripType));

            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentChanger_ConstraitLayout, new TripListFragment()).commit();

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_MenuItem:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentChanger_ConstraitLayout, new AddItemFragment()).commit();
                return true;
            case R.id.home_MenuItem:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentChanger_ConstraitLayout, new TripListFragment()).commit();
                return true;
            default:
                return false;
        }
    }

}