package org.example;

import java.util.Objects;

public class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
    public Song(){

    }

    public String getTitle() {
        return title;
    }

    public String  getArtist () {
        return artist;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }


    @Override
    public String toString() {
        return "{ " +
                "\"Title\" : \"" + title + "\", " +
                "\"Artist\" : \"" + artist + "\" " +
                "}";
    }
}
