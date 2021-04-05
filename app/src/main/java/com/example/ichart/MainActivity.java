package com.example.ichart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ichart.fragments.SonglistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";


    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_location:
                        //TODO: add locationFragment()
                        Toast.makeText(MainActivity.this, "Go to Location Page", Toast.LENGTH_SHORT).show();
                        fragment =
                        break;
                    case R.id.action_songList:
                        fragment = new SonglistFragment(MainActivity.this);
                        break;
                    case R.id.action_profile:
                    default:
                        //TODO: add ProfileFragment();
                        Toast.makeText(MainActivity.this, "Go to Profile Page", Toast.LENGTH_SHORT).show();
                        fragment = new SonglistFragment(getContext(), allSongs);
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        //Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_songList);
    }
}

