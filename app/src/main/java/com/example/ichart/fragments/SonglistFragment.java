package com.example.ichart.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ichart.R;
import com.example.ichart.Song;
import com.example.ichart.SongsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SonglistFragment extends Fragment {

    public static final String TAG = "SonglistFragment";
    private RecyclerView rvSonglist;
    protected SongsAdapter adapter;
    protected List<Song> allSongs;

    public SonglistFragment(Context context, List<Song> allSongs) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSonglist = view.findViewById(R.id.rvSonglist);
        allSongs = new ArrayList<Song>();
        adapter = new SongsAdapter(getContext(), allSongs);

        //Steps to use the recycler view:
        //0. create layout for one row in the list
        //1. create the adapter
        //2. create the data source
        //3. set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //4. set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

    }
}
