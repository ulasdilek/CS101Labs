package lab9;

import java.util.ArrayList;
import java.util.Scanner;

public class MusicApp {
    public static void main (String[] args)
    {
        ArrayList<Playlist> playlists = new ArrayList<>();
        Playlist playlist = null;
        String[] genres = {"Country", "Electronic", "Pop", "Rock", "Jazz", "Classical"};
        Scanner in = new Scanner(System.in);
        boolean appOn = true;
        boolean playlistSelected = false;

        while (appOn) {
            if (!playlistSelected && !playlists.isEmpty()) {
                playlist = playlists.get(playlists.size() - 1);
            }
            System.out.println("1 - Create a new Playlist\n2 - Display the current Playlist\n3 - Sort the current Playlist\n4 - Add a new song to the current playlist\n5 - Remove song by index from the current Playlist\n6 - Display all Playlists\n7 - Select a playlist by index\n8 - Exit");
            int response = onlyNextInt(in);
            if (response == 1) {
                playlist = new Playlist();
                addSongs(playlist, in);
                System.out.println("Enter playlist name");
                playlist.setName(in.nextLine());
                System.out.println("Enter the creator of the playlist");
                playlist.setCreator(in.nextLine());
                System.out.println("0-Country\n1-Electronic\n2-Pop\n3-Rock\n4-Jazz\n5-Classical\nSelect the genre of the playlist");
                playlist.setGenre(genres[onlyNextInt(in)]);
                playlists.add(playlist);
            }
            else if (playlists.isEmpty() && response != 8) {
                System.out.println("There is no playlist to work with. Please add new playlists.");
            }
            else if (response == 2) {
                System.out.println("Displaying the playlist: " + playlist.getName());
                System.out.println(playlist);
            }
            else if (response == 3) {
                System.out.println("Sorted playlist:");
                playlist.sortByDurationDesc();
                System.out.println(playlist);
            }
            else if (response == 4) {
                addSongs(playlist, in);
            }
            else if (response == 5) {
                System.out.println("Which song do you want to remove? (by index)");
                int remove = onlyNextInt(in);
                if (remove < 0 || remove >= playlist.getNumberOfSongs()) {
                    System.out.println("Invalid index!");
                }
                else {
                    playlist.removeSong(remove);
                }
            }
            else if (response == 6) {
                System.out.println("All playlists:");
                for (Playlist pl: playlists) {
                    System.out.println(pl);
                }
            }
            else if (response == 7) {
                int upperBound = playlists.size() - 1;
                System.out.println("Which playlist do you want to select? (by index 0 - " + upperBound);
                int index = onlyNextInt(in);
                if (index < 0 || index > upperBound) {
                    System.out.println("Invalid playlist index!");
                }
                else {
                    playlist = playlists.get(index);
                    playlistSelected = true;
                }
                System.out.println("Current playlist is " + playlist.getName());
            }
            else if (response == 8) {
                in.close();
                appOn = false;
            }
            else {
                System.out.println("Incorrect input!");
            }
        }
    }

    private static int onlyNextInt (Scanner scanner) {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private static void addSongs (Playlist playlist, Scanner scanner) {
        System.out.println("How many songs do you want to add to the playlist?");
        int newSongs = onlyNextInt(scanner);
        for (int i = 1; i <= newSongs; i++) {
            System.out.println("Enter the artist name for Song " + i);
            String artist = scanner.nextLine();
            System.out.println("Enter the album name for Song " + i);
            String album = scanner.nextLine();
            System.out.println("Enter the song name for Song " + i);
            String title = scanner.nextLine();
            System.out.println("Enter the song duration in seconds for Song " + i);
            int duration = onlyNextInt(scanner);
            System.out.println("How many times has Song " + i + " been listened to?");
            int timesPlayed = onlyNextInt(scanner);
            playlist.addSong(new Song(title, artist, album, duration, timesPlayed));
        }
    }
}
