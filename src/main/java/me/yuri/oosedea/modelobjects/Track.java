package me.yuri.oosedea.modelobjects;

import java.util.Objects;

public class Track {

    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int playcount;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;

    public Track() {}

    public void setId(int id) {
        this.id = id;
    }
    public int getId() { return id; }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() { return title; }
    public void setPerformer(String performer) {
        this.performer = performer;
    }
    public String getPerformer() { return performer; }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() { return duration; }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getAlbum() { return album; }
    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }
    public int getPlaycount() { return playcount; }
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    public String getPublicationDate() { return publicationDate; }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() { return description; }
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
    public boolean isOfflineAvailable() { return offlineAvailable; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id) &&
                Objects.equals(title, track.title) &&
                Objects.equals(performer, track.performer) &&
                Objects.equals(duration, track.duration) &&
                Objects.equals(album, track.album) &&
                Objects.equals(playcount, track.playcount) &&
                Objects.equals(publicationDate, track.publicationDate) &&
                Objects.equals(description, track.description) &&
                Objects.equals(offlineAvailable, track.offlineAvailable);
    }
}
