package com.example.ichart.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.ichart.R;
import com.example.ichart.Song;
import com.example.ichart.SongsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class SonglistFragment extends Fragment {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";

    List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_songlist);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        //Create the adapter
        MovieAdapter movieAdapter = new MovieAdapter(this,movies);

        // set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter);

        //set a layout manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG,"Results:" + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG,"Movies:" + movies.size());
                }catch (JSONException e) {
                    Log.e(TAG, "Hit Json Exception", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG,"onFailure");
            }
        });
    }
}
