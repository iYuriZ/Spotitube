package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.rest.dto.DTO;

public class TrackRequest implements DTO {

    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int playcount;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;

    public TrackRequest() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPerformer(String performer) {
        this.performer = performer;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setAlbum(String album) { this.album = album; }
    public void setPlaycount(int playcount) { this.playcount = playcount; }
    public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }
    public void setDescription(String description) { this.description = description; }
    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
