package me.yuri.oosedea.modelobjects;

import java.util.Objects;

public class Playlist {

    private int id;
    private String name;
    private boolean owner;
    private int[] tracks;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isOwner() {
        return owner;
    }
    public void setOwner(boolean owner) {
        this.owner = owner;
    }
    public int[] getTracks() {
        return tracks;
    }
    public void setTracks(int[] tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(id, playlist.id) &&
                Objects.equals(name, playlist.name) &&
                Objects.equals(owner, playlist.owner) &&
                Objects.equals(tracks, playlist.tracks);
    }
}
