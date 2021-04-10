package com.example.ichart.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Song {

    int song_id;
    int artist_id;
    //int song_totalListener;

    String song_description;
    String song_artist;
    String song_track;
    String song_genra;
    String song_youtube_video_link;
    String song_track_thumb;

    public Song(JSONObject jsonObject) throws JSONException{
        song_id = jsonObject.getInt("idTrack");
        artist_id = jsonObject.getInt("idArtist");
        //song_totalListener = jsonObject.getInt("intTotalListeners");

        song_description = jsonObject.getString("strDescription");
        song_artist = jsonObject.getString("strArtist");
        song_track = jsonObject.getString("strTrack");
        song_genra = jsonObject.getString("strGenre");
        song_youtube_video_link = jsonObject.getString("strMusicVid");
        song_track_thumb = jsonObject.getString("strTrackThumb");
    }

    public static List<Song> fromJsonArray(JSONArray songJsonArray) throws JSONException {
        List<Song> songs= new ArrayList<>();
        for (int i = 0; i < songJsonArray.length(); i++) {
            songs.add(new com.example.ichart.models.Song(songJsonArray.getJSONObject(i)));
        }
        return songs;
    }

    public int getSong_id() {
        return song_id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    //public int getSong_totalListener() {
        //return song_totalListener;
    //}

    public String getSong_description() {
        return song_description;
    }

    public String getSong_artist() {
        return song_artist;
    }

    public String getSong_track() {
        return song_track;
    }

    public String getSong_genra() {
        return song_genra;
    }

    public String getSong_youtube_video_link() {
        return song_youtube_video_link;
    }

    public String getSong_track_thumb() {
        return song_track_thumb;
    }
}
