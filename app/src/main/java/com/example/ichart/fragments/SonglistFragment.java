package com.example.ichart.fragments;

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
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.ichart.R;
import com.example.ichart.SongsAdapter;
import com.example.ichart.models.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class SonglistFragment extends Fragment {

    public static final String API_URL = "https://theaudiodb.com/api/v1/json/523532/mostloved.php?format=track";
    //api_key 523532
    public static final String TAG = "SonglistFragment";

    List<Song> songs;

    public SonglistFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView rvSonglist = view.findViewById(R.id.rvSonglist);
        songs = new ArrayList<>();

        //Create the adapter
        SongsAdapter songsAdapter = new SongsAdapter(getContext(), songs);

        // set the adapter on the recycler view
        rvSonglist.setAdapter(songsAdapter);

        //set a layout manager on the recycler view
        rvSonglist.setLayoutManager(new LinearLayoutManager(getContext()));

        AsyncHttpClient client = new AsyncHttpClient();


        //The api key are still requesting, so the song list did not show up.
        RequestParams params = new RequestParams();
        params.put("limit", "5");
        params.put("page", 0);

        client.get(API_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess");
                JSONObject songObject = json.jsonObject;
                try {
                    if (songObject.optString("strTrackThumb") != null && songObject.optString("strMusicVid") != null
                    && songObject.optString("strDescription") != null && songObject.optString("strArtist") != null
                    && songObject.optString("strTrack") != null && songObject.optString("strGenre") != null) {
                        JSONArray results = songObject.getJSONArray("loved");
                        songs.addAll(Song.fromJsonArray(results));
                    }
                    //Log.i(TAG,"Results:" + results.toString());

                    songsAdapter.notifyDataSetChanged();
                    Log.i(TAG,"Songs:" + songs.size());
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
