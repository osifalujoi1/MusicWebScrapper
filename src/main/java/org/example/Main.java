package org.example;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


import java.io.IOException;
import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

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
                    printTop10();
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
                "7 - Generate Iconic Playlist");
    }

    private static void printList(LinkedList<Song> playList){
        Iterator<Song> iterator = playList.iterator();
        System.out.println("---------------");

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("----------------");

    }

    private static void printTop10 () {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.billboard.com/charts/hot-100/")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4.1 Safari/605.1.15")
                    .header("Accept-Language", "*").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LinkedList<Song> iconicPlaylist = new LinkedList<>();

        Elements songsElements = doc.select("ul.lrv-a-unstyle-list");
        //Elements extends an ArrayList, so you can easily iterate over it.
        for (Element songsElement : songsElements){
            Song song = new Song(
                    (songsElement.select("h3#title-of-a-story.c-title").text().trim()),
                    (songsElement.select("span.c-label.a-no-trucate").text().trim()));
            if (!(song.getTitle().equals("")) || !(song.getArtist().equals(""))) {
                iconicPlaylist.add(song);
            }
        }

        Iterator<Song> iterator = iconicPlaylist.iterator();


        System.out.println("---------------");

        for (int i = 0; i <= iconicPlaylist.size() - 2; i = i + 2){
            System.out.println("1. " + iconicPlaylist.get(i));
        }

        System.out.println("----------------");
    }
}

//BILLBOARD
    //element = li.o-chart-results-list-row-container
        //h3#title-of-a-story.a-no-trucate

//SPOTIFY
    //element
//<div data-testid="tracklist-row" class="IjYxRc5luMiDPhKhZVUH UpiE7J6vPrJIa59qxts4" draggable="true" role="presentation"><div class="fS0C4IgbHviZxIVGC736" role="gridcell" aria-colindex="1"><div class="ucB9avGYvzsmzXUOw0S7"><span class="encore-text encore-text-body-medium xNyTkXEncSjszLNI65Nq" data-encore-id="text">1</span><button class="j2s64Lz8y6VzBLB_V9Gm" aria-label="Play BIRDS OF A FEATHER by Billie Eilish" tabindex="-1"><svg data-encore-id="icon" role="img" aria-hidden="true" class="Svg-sc-ytk21e-0 bneLcE zOsKPnD_9x3KJqQCSmAq" viewBox="0 0 24 24"><path d="m7.05 3.606 13.49 7.788a.7.7 0 0 1 0 1.212L7.05 20.394A.7.7 0 0 1 6 19.788V4.212a.7.7 0 0 1 1.05-.606z"></path></svg></button></div></div><div class="w46g_LQVSLE9xK399VYf" role="gridcell" aria-colindex="2"><img aria-hidden="false" draggable="false" loading="eager" src="https://i.scdn.co/image/ab67616d0000485171d62ea7ea8a5be92d3c1f62" alt="" class="mMx2LUixlnN_Fu45JpFB IqDKYprOtD_EJR1WClPv Yn2Ei5QZn19gria6LjZj" width="40" height="40" style="border-radius: 4px;"><div class="_iQpvk1c9OgRAc8KRTlH"><a draggable="false" class="btE2c3IKaOXZ4VNAb8WQ" data-testid="internal-track-link" href="/track/6dOtVTDdiauQNBQEDOtlAB" tabindex="-1"><div class="encore-text encore-text-body-medium encore-internal-color-text-base btE2c3IKaOXZ4VNAb8WQ standalone-ellipsis-one-line" data-encore-id="text" dir="auto">BIRDS OF A FEATHER</div></a><span class="encore-text encore-text-body-small encore-internal-color-text-subdued UudGCx16EmBkuFPllvss standalone-ellipsis-one-line" data-encore-id="text"><div class="encore-text encore-text-body-small" data-encore-id="text"><a draggable="true" dir="auto" href="/artist/6qqNVTkY8uBg9cP3Jd7DAH" tabindex="-1">Billie Eilish</a></div></span></div></div><div class="_TH6YAXEzJtzSxhkGSqu" role="gridcell" aria-colindex="3"><span><span class="encore-text encore-text-body-small" data-encore-id="text"><a draggable="true" class="standalone-ellipsis-one-line" dir="auto" href="/album/7aJuG4TFXa2hmE4z1yxc3n" tabindex="-1">HIT ME HARD AND SOFT</a></span></span></div><div class="_TH6YAXEzJtzSxhkGSqu" role="gridcell" aria-colindex="4"><span class="encore-text encore-text-body-small encore-internal-color-text-subdued" data-encore-id="text">6 days ago</span></div><div class="PAqIqZXvse_3h6sDVxU0" role="gridcell" aria-colindex="5"><button aria-checked="false" data-testid="add-button" class="Button-sc-1dqy6lx-0 dWJgbx GcODM2Bp3srQqJzi8Tzs" aria-label="Save to Your Library" data-encore-id="buttonTertiary" tabindex="-1"><span aria-hidden="true" class="IconWrapper__Wrapper-sc-16usrgb-0 hYdsxw"><svg data-encore-id="icon" role="img" aria-hidden="true" viewBox="0 0 16 16" class="Svg-sc-ytk21e-0 dYnaPI"><path d="M1.69 2A4.582 4.582 0 0 1 8 2.023 4.583 4.583 0 0 1 11.88.817h.002a4.618 4.618 0 0 1 3.782 3.65v.003a4.543 4.543 0 0 1-1.011 3.84L9.35 14.629a1.765 1.765 0 0 1-2.093.464 1.762 1.762 0 0 1-.605-.463L1.348 8.309A4.582 4.582 0 0 1 1.689 2zm3.158.252A3.082 3.082 0 0 0 2.49 7.337l.005.005L7.8 13.664a.264.264 0 0 0 .311.069.262.262 0 0 0 .09-.069l5.312-6.33a3.043 3.043 0 0 0 .68-2.573 3.118 3.118 0 0 0-2.551-2.463 3.079 3.079 0 0 0-2.612.816l-.007.007a1.501 1.501 0 0 1-2.045 0l-.009-.008a3.082 3.082 0 0 0-2.121-.861z"></path></svg></span></button><div class="encore-text encore-text-body-small encore-internal-color-text-subdued l5CmSxiQaap8rWOOpEpk" data-encore-id="text">3:30</div><button aria-haspopup="menu" data-testid="more-button" class="Button-sc-1dqy6lx-0 dWJgbx ObVor_8sQq5whKbtWs8a" aria-label="More options for BIRDS OF A FEATHER by Billie Eilish" data-encore-id="buttonTertiary" tabindex="-1"><span aria-hidden="true" class="IconWrapper__Wrapper-sc-16usrgb-0 hYdsxw"><svg data-encore-id="icon" role="img" aria-hidden="true" viewBox="0 0 16 16" class="Svg-sc-ytk21e-0 dYnaPI"><path d="M3 8a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm6.5 0a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zM16 8a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"></path></svg></span></button></div></div>
    // ARTIST <a draggable="true" dir="auto" href="/artist/6qqNVTkY8uBg9cP3Jd7DAH" tabindex="-1">Billie Eilish</a>
    // DURATION <div class="encore-text encore-text-body-small encore-internal-color-text-subdued l5CmSxiQaap8rWOOpEpk" data-encore-id="text">3:30</div>


// <div class="_iQpvk1c9OgRAc8KRTlH"><a draggable="false" class="btE2c3IKaOXZ4VNAb8WQ" data-testid="internal-track-link" href="/track/6dOtVTDdiauQNBQEDOtlAB" tabindex="-1"><div class="encore-text encore-text-body-medium encore-internal-color-text-base btE2c3IKaOXZ4VNAb8WQ standalone-ellipsis-one-line" data-encore-id="text" dir="auto">BIRDS OF A FEATHER</div></a><span class="encore-text encore-text-body-small encore-internal-color-text-subdued UudGCx16EmBkuFPllvss standalone-ellipsis-one-line" data-encore-id="text"><div class="encore-text encore-text-body-small" data-encore-id="text"><a draggable="true" dir="auto" href="/artist/6qqNVTkY8uBg9cP3Jd7DAH" tabindex="-1">Billie Eilish</a></div></span></div>
// SONG NAME <div class="encore-text encore-text-body-medium encore-internal-color-text-base btE2c3IKaOXZ4VNAb8WQ standalone-ellipsis-one-line" data-encore-id="text" dir="auto">BIRDS OF A FEATHER</div>