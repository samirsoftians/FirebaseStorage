package com.softianstech.firebasestorage;

/**
 * Created by Lenovo on 11/10/2017.
 */

public class Artist {
    String artistID,name,genre;

   public Artist()
    {

    }

    public Artist(String artistID, String name, String genre) {
        this.artistID = artistID;
        this.name = name;
        this.genre = genre;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
