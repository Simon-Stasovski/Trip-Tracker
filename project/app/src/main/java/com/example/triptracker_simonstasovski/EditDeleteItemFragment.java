package com.example.triptracker_simonstasovski;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.triptracker_simonstasovski.databinding.FragmentEditDeleteItemBinding;
import com.example.triptracker_simonstasovski.model.Trip;
import com.example.triptracker_simonstasovski.model.TripTypes;

import java.sql.Time;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EditDeleteItemFragment extends Fragment {

    private FragmentEditDeleteItemBinding binding;
    private Trip mtrip;
    private Button editButton, deleteButton;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int startOdometer, endOdometer;

    public EditDeleteItemFragment(Trip trip) {
        this.mtrip = trip;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditDeleteItemBinding.inflate(inflater, container, false);

        editButton = binding.editButton;
        deleteButton = binding.deleteButton;
        datePicker = binding.newTripDatePicker;
        timePicker = binding.newTripTimePicker;
        startOdometer = Integer.parseInt(binding.startOdometerEditText.getText().toString());
        endOdometer = Integer.parseInt(binding.endOdometerEditText.getText().toString());

        datePicker.updateDate(mtrip.getDateOfTrip().getYear() + 1900,
                mtrip.getDateOfTrip().getMonth(),
                mtrip.getDateOfTrip().getDate());
        timePicker.setHour(mtrip.getTripStartTime().getHours());
        timePicker.setMinute(mtrip.getTripStartTime().getMinutes());

        EditText editTextStart = (EditText) binding.startOdometerEditText;
        EditText editTextEnd = (EditText) binding.endOdometerEditText;

        editTextStart.setText((mtrip.getStartOdometer() + ""));
        editTextEnd.setText(mtrip.getEndOdometer() + "");

        editButton.setOnClickListener(v -> {

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

            MainActivity.tripRepository.update(new Trip(mtrip.getId(), new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()),
                    new Time(timePicker.getHour(), timePicker.getMinute(), 0), startOdometer, endOdometer, tripType));

            AppCompatActivity activity = (AppCompatActivity) v.getContext();

            activity.getSupportFragmentManager().beginTransaction().add(R.id.fragmentChanger_ConstraitLayout, MainActivity.listFragment).commit();
        });

        deleteButton.setOnClickListener(v -> {
            MainActivity.tripRepository.delete(mtrip);
            AppCompatActivity activity = (AppCompatActivity) v.getContext();

            activity.getSupportFragmentManager().beginTransaction().add(R.id.fragmentChanger_ConstraitLayout, MainActivity.listFragment).commit();

        });

        return binding.getRoot();
    }
}