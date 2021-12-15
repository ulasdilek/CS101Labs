package lab9;

public class Song implements Comparable<Song> {

    // static variables
    private final int INVALID_VALUE = -1;

    // instance variables
    private String title;
    private String artist;
    private String album;
    private int duration;
    private int timesPlayed;

    // constructors
    public Song(String title, String artist, String album, int duration, int timesPlayed)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        setDuration(duration);
        setTimesPlayed(timesPlayed);
    }

    // accessors

    public String getTitle ()
    {
        return title;
    }

    public String getArtist ()
    {
        return artist;
    }

    public String getAlbum ()
    {
        return album;
    }

    public int getDuration ()
    {
        return duration;
    }

    public int getTimesPlayed ()
    {
        return timesPlayed;
    }

    // mutators
    public void setTitle (String title)
    {
        this.title = title;
    }

    public void setArtist (String artist)
    {
        this.artist = artist;
    }

    public void setAlbum (String album)
    {
        this.album = album;
    }

    public void setDuration (int duration)
    {
        if (duration > 0) {
            this.duration = duration;
        }
        else {
            System.out.println("duration property of the song " + title + " is invalid!");
            this.duration = INVALID_VALUE;
        }
    }

    public void setTimesPlayed (int timesPlayed)
    {
        if (timesPlayed > 0) {
            this.timesPlayed = timesPlayed;
        }
        else {
            System.out.println("timesPlayed property of the song " + title + " is invalid!");
            this.timesPlayed = INVALID_VALUE;
        }
    }

    // instance methods
    @Override
    public int compareTo (Song otherSong)
    {
        return duration - otherSong.duration;
    }

    @Override
    public String toString ()
    {
        return "Song Name: " + title + "\nArtist Name: " + artist + "\nAlbum Name: " + album + "\nDuration: " + formattedDuration() + "\nTimes Played: " + timesPlayed;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj instanceof Song) {
            Song song = (Song) obj;
            return duration == song.duration && song.title.equals(song.title) && album.equals(song.album) && artist.equals(song.artist);
        }
        return false;
    }

    // static methods


    // private methods
    private String formattedDuration ()
    {
        int second = duration % 60;
        int minute = (duration / 60) % 60;
        int hour = duration / 3600;
        String result;
        if (hour == 0) {
            if (minute == 0) {
                result = String.valueOf(second);
            }
            else {
                result = String.format("%2d%s%2d", minute, ":", second);
            }
        }
        else {
            result = String.format("%2d%s%2d%s%2d", hour, ":", minute, ":", second);
        }
        return result;
    }

}
