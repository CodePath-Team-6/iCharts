package com.example.ichart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ichart.fragments.SonglistFragment;
import com.example.ichart.models.Song;

import org.parceler.Parcels;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    Context context;
    List<Song> songs;

    public SongsAdapter(Context context, List<Song> songs){
        this.context = context;
        this.songs = songs;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View songView = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(songView);
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("SongsAdapter", "onBindViewHolder" + position);
        //Get the song at the passed in position
        Song song = songs.get(position);
        //Bind the song data into the VH
        holder.bind(song);

    }

    //Returns the total count of items in the List
    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvAuthor;
        TextView tvSongname;
        TextView tvListener;
        ImageView ivCover;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvSongname = itemView.findViewById(R.id.tvSongname);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            //tvListener = itemView.findViewById(R.id.tvListerner);
            ivCover = itemView.findViewById(R.id.ivCover);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Song song) {
            tvSongname.setText(song.getSong_track());
            tvAuthor.setText(song.getSong_artist());
            //tvListener.setText(song.getSong_totalListener());

            Glide.with(context).load(song.getSong_track_thumb()).into(ivCover);

            tvSongname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MediaPlayer.class);

                    i.putExtra("title", song.getSong_track());
                    i.putExtra("song", Parcels.wrap(song));
                    context.startActivity(i);
                }
            });
        }
    }


}


