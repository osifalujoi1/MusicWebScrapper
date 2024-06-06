package org.example;

import java.util.Objects;

public class Song {
    private String title;
    private double duration;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
//        this.duration = duration;
        this.artist = artist;
    }
    public Song(){

    }

    public String getTitle() {
        return title;
    }

//    public double getDuration() {
//        return duration;
//    }

    public String  getArtist () {
        return artist;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

//    public void setDuration(double duration) {
//        this.duration = duration;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "{ " +
                "\"Title\" : \"" + title + "\", " +
                "\"Duration\" : \"" + duration + "\", " +
                "\"Artist\" : \"" + artist + "\" " +
                "}";
    }
}
