package com.example.ichart;


import com.parse.ParseObject;
import com.parse.ParseUser;

public class Song extends ParseObject {

    public static final String KEY_AUTHOR = "Author";
    public static final String KEY_SONGNAME = "Songname";
    public static final String KEY_GENRA = "genra";
    public static final String KEY_LISTENER = "listener";

    public String getAuthor(){
        return getString(KEY_AUTHOR);

    }

    public void setAuthor(String author){
        put(KEY_AUTHOR, author);

    }


    public ParseUser getUser(){
        return getParseUser(KEY_GENRA);
    }
}
