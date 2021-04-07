package com.example.ichart;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.ichart.models.Song;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MediaPlayer extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "45612d819cmsh3dbf06d01dbd2fcp156bebjsn4e67c4ceb14e";

    public  static final String VIDEOS_URL = "https://theaudiodb.p.rapidapi.com/searchtrack.php?s=coldplay&t=yellow";

    TextView tvTitle;
    TextView tvOverview;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        youTubePlayerView = findViewById(R.id.player);
        Song song = Parcels.unwrap(getIntent().getParcelableExtra("movie"));//1
        tvTitle.setText(song.getSong_track());
        tvOverview.setText(song.getSong_description());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, song.getSong_youtube_video_link()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length()==0){
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");//2
                    Log.d("DataActivity",youtubeKey);
                    initializeYoutube(youtubeKey);
                } catch (JSONException e) {
                    Log.e("DataActivity","Failed to parse JSON",e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
    }

    private void initializeYoutube(String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DataActivity","onInitializationSuccess");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DataActivity","onInitializationFailure");

            }
        });
    }
}