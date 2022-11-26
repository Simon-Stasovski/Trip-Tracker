package com.example.triptracker_simonstasovski;

import android.os.Bundle;

import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.triptracker_simonstasovski.databinding.FragmentTripListBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TripListFragment extends Fragment {

    FragmentTripListBinding binding;

    public TripListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTripListBinding.inflate(inflater, container, false);
        binding.tripsRecyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.tripsRecyclerView.setItemAnimator(null);
        binding.tripsRecyclerView.setAdapter(MainActivity.adapter);
        return binding.getRoot();
    }
}