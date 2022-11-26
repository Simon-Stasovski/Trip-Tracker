package com.example.triptracker_simonstasovski;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.triptracker_simonstasovski.model.Trip;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static TripRepositoryImpl tripRepository;
    public static Fragment listFragment;
    public static TripRecyclerViewAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripRepository = new TripRepositoryImpl();
        setContentView(R.layout.activity_main);
        listFragment = new TripListFragment();
        adapter = new TripRecyclerViewAdapter(tripRepository.trips);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChanger_ConstraitLayout, listFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchButton = menu.findItem(R.id.app_BarSearch);
        SearchView searchView = (SearchView) searchButton.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChanger_ConstraitLayout, new TripListFragment()).commit();
                return true;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_MenuItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChanger_ConstraitLayout, new AddItemFragment()).commit();
                return true;
            case R.id.home_MenuItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChanger_ConstraitLayout, listFragment).commit();
                return true;
            default:
                return false;
        }
    }
}