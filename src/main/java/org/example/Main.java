package org.example;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.Collection;
import java.io.IOException;
import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();
    private static LinkedList<Song> iconicTopBillboard = new LinkedList<>();
    private static LinkedList<Song> iconicTopLastfm = new LinkedList<>();
    private static LinkedList<Song> iconicBBCPlaylist = new LinkedList<>();
    private static LinkedList<Song> iconicPlaylist = new LinkedList<>();
    private static LinkedList<Song> SuggestedForYou = new LinkedList<>();

    public static void main(String[] args) {

        Album album1 = new Album("Album1", "AC/DC");
        album1.addSong("TNT", "AC/DC");
        album1.addSong("Highway to hell","AC/DC");
        album1.addSong("ThunderStruck", "AC/DC");

        Album album2 = new Album("Album2", "TopSongs");
        album2.addSong("Rad god", "Eminem");
        album2.addSong("Not Afraid", "Eminem");
        album2.addSong("Lose Yourself", "Eminem");

        albums.add(album1);
        albums.add(album2);

        LinkedList<Song> playlist1 = new LinkedList<>();

        albums.get(0).addToPlayList("TNT", playlist1);
        albums.get(0).addToPlayList("Highway to hell", playlist1);
        albums.get(1).addToPlayList("Rad god", playlist1);
        albums.get(1).addToPlayList("Lose Yourself", playlist1);

        play(playlist1);
    }

    private static void play(LinkedList<Song> playList){
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if(playList.size() == 0) {
            System.out.println("This playList have no song");
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit){
            int action = sc.nextInt();
            sc.nextLine();

            switch (action){
                case 0 :
                    System.out.println("Playlist complete");
                    quit = true;
                    break;
                case 1:
                    if (!forward){
                        if (listIterator.hasNext()){
                            listIterator.next();//Go to the next song if it exists, and you're not forwarding.
                        }
                        forward = true; //then forward
                    }
                    if (listIterator.hasNext()){
                        System.out.println("Now Playing " + listIterator.next().toString());
                    } else {
                        System.out.println("No song available, reached to the end of the list");
                        forward = false; //there is no song after the current song.
                    }
                    break;
                case 2:
                    if (forward){
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("You've reached the top of the playlist.");
                        forward = false;
                    }break;
                case 3:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            System.out.println("Now playing "+listIterator.previous().toString());
                            forward = false;
                        }else {
                            System.out.println("You've reached the top of the playlist");
                        }
                    }else {
                        if(listIterator.hasNext()){
                            System.out.println("Now playing "+listIterator.next().toString());
                            forward = true;
                        }else {
                            System.out.println("This is the last song on the list");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playList.size() > 0){
                        listIterator.remove();
                        if (listIterator.hasNext()){
                            System.out.println("Now playing " + listIterator.next().toString());
                        } else {
                            if (listIterator.hasNext())
                                System.out.println("Now playing " + listIterator.previous().toString());
                        }
                    }
                    break;
                case 7:
                    setTopBillboard();
                    System.out.println("TOP SONGS ON BILLBOARD.COM");
                    printList(iconicTopBillboard);
                    break;
                case 8:
                    setTopLastfm();
                    System.out.println("TOP SONGS ON LAST.FM");
                    printList(iconicTopLastfm);
                    break;
                case 9:
                    setTopBBCRadio();
                    System.out.println("TOP SONGS ON BBC RADIO");
                    printList(iconicBBCPlaylist);
                    break;
                case 10:
                    printIconicPlaylist();
            }
        }
    }
    private static void printMenu () {
        System.out.println("Available options\n press");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - List of all songs \n" +
                "5 - Print all available options\n" +
                "6 - To delete current song\n" +
                "7 - View BillBoard.com Top Songs\n" +
                "8 - View Last.fm Top Songs \n" +
                "9 - View BBC Radio Top songs \n" +
                "10 - Generate Iconic Playlist\n");
    }

    private static void printList(LinkedList<Song> playList){
        int index = 0;
        Iterator<Song> iterator = playList.iterator();
        System.out.println("---------------");

        while (iterator.hasNext()){
            index++;
            System.out.println(index + ". " + iterator.next());
        }

        System.out.println("----------------");

    }

    private static void setTopBillboard () {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.billboard.com/charts/hot-100/")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4.1 Safari/605.1.15")
                    .header("Accept-Language", "*").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LinkedList<Song> songs = new LinkedList<>();
        Elements songsElements = doc.select("ul.lrv-a-unstyle-list");
        //Elements extends an ArrayList, so you can easily iterate over it.
        for (Element songsElement : songsElements){
            Song song = new Song(
                    (songsElement.select("h3#title-of-a-story.c-title").text().trim()),
                    (songsElement.select("span.c-label.a-no-trucate").text().trim()));
            if (!(song.getTitle().equals("")) || !(song.getArtist().equals(""))) {
                songs.add(song);
            }
        }

        for (int i = 0; i <= songs.size() - 2; i = i + 2){
            iconicTopBillboard.add(songs.get(i));
        }
    }

    private static void setTopLastfm () {
        Document doc;
        try{
            doc = Jsoup.connect("https://www.last.fm/charts")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4.1 Safari/605.1.15")
                    .header("Accept-Language", "*")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements songsElement = doc.select("tr.js-link-block");
        for (Element songElement : songsElement){
            Song song = new Song(
                    (songElement.select("td").select("a.link-block-target").text()),
                    (songElement.select("td.globalchart-track-artist-name").select("a").text())
            );
            iconicTopLastfm.add(song);
        }


    }

    private static void setTopBBCRadio () {
        Document doc;
        try{
            doc = Jsoup.connect("https://www.officialcharts.com/charts/singles-chart/")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4.1 Safari/605.1.15")
                    .header("Accept-Language", "*")
                    .get();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements songElements = doc.select("div.description.block");
        for (Element songElement : songElements){
            Song song = new Song(
                    (songElement.select("p").select("a.chart-name").select("span").text()),
                    (songElement.select("p").select("a.chart-artist").select("span").text())
            );
            iconicBBCPlaylist.add(song);
        }

    }

    private static void printIconicPlaylist () {
        if (iconicTopBillboard.isEmpty()){
            setTopBillboard();
        }
        if (iconicTopLastfm.isEmpty()){
            setTopLastfm();
        }
        if (iconicBBCPlaylist.isEmpty()){
            setTopBBCRadio();
        }
        iconicPlaylist.addAll(iconicTopBillboard);
        iconicPlaylist.addAll(iconicTopLastfm);
        iconicPlaylist.addAll(iconicBBCPlaylist);

        //to find songs that appear at least three times
        //create an hash table with the title of the songs mapped to how often they occur
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        //create a temporary list to store songs that appear in multiple playlists
        LinkedList<Song> temp = new LinkedList<>();

        //create a hashset to check for multiple entries
        HashSet<String> addedSongs = new HashSet<>();

        for (Song song : iconicPlaylist){
            String title = song.getTitle().toLowerCase();
            if (hashtable.containsKey(title)){
                hashtable.put(title, hashtable.get(title) + 1);
                if (hashtable.get(title) >= 2  && addedSongs.add(title)){
                    temp.add(song);
                }
            }
            else{
                hashtable.put(title, 1);
            }
        }
        iconicPlaylist.clear();

        if (temp.size() > 10){
            Collections.shuffle(temp);
            for (int i = 0; i < 10; i++){
                iconicPlaylist.add(temp.get(i));
            }
        } else{
            iconicPlaylist.addAll(temp);
        }

        printList(iconicPlaylist);
    }
}
