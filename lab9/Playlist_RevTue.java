package lab9;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist_RevTue implements Comparable<Playlist_RevTue> {

    // static variables
    private final String UNDEFINED = "NA";

    // instance variables
    private String name;
    private String creator;
    private String genre;
    private ArrayList<Song> songs;
    private int numberOfSongs;

    // constructors
    public Playlist_RevTue ()
    {
        name = UNDEFINED;
        creator = UNDEFINED;
        genre = UNDEFINED;
        songs = new ArrayList<Song>();
        numberOfSongs = 0;
    }

    public Playlist_RevTue (String name, String creator, String genre, ArrayList<Song> songs)
    {
        this.name = name;
        this.creator = creator;
        this.genre = genre;
        setSongs(songs);
    }

    // accessors
    public String getName ()
    {
        return name;
    }

    public String getCreator ()
    {
        return creator;
    }

    public String getGenre ()
    {
        return genre;
    }

    public ArrayList<Song> getSongs ()
    {
        return songs;
    }

    public int getNumberOfSongs ()
    {
        return numberOfSongs;
    }

    // mutators
    public void setName (String name)
    {
        this.name = name;
    }

    public void setCreator (String creator)
    {
        this.creator = creator;
    }

    public void setGenre (String genre)
    {
        this.genre = genre;
    }

    public void setSongs (ArrayList<Song> songs)
    {
        numberOfSongs = songs.size();
        this.songs = songs;
    }

    // instance methods
    public boolean songExists (Song song)
    {
        for (Song s : songs) {
            if (s.equals(song)) {
                return true;
            }
        }
        return false;
    }

    public void addSong (Song song)
    {
        if (!songExists(song)) {
            songs.add(song);
            numberOfSongs++;
        }
    }

    public void removeSong (int index)
    {
        songs.remove(index);
        numberOfSongs--;
    }

    public void sortByDurationDesc ()
    {
        Collections.sort(songs, Collections.reverseOrder());
    }

    @Override
    public int compareTo (Playlist_RevTue otherPlaylist) {
        return numberOfSongs - otherPlaylist.numberOfSongs;
    }

    @Override
    public String toString ()
    {
        String result = "---------------------------------------------------------\nPlaylist Name: " + name + "\nCreator Name: " + creator + "\nGenre: " + genre + "\n---------------------------------------------------------";
        for (Song song: songs) {
            result = result + "\n" + song + "\n---------------------------------------------------------";
        }
        return result;
    }

    // static methods


    // private methods

}
