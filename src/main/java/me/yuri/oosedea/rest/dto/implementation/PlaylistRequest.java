package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.rest.dto.DTO;

public class PlaylistRequest implements DTO {

    private int id;
    private String name;
    private boolean owner;
    private int[] tracks;

    private PlaylistRequest() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isOwner() { return owner; }
    public void setOwner(boolean owner) { this.owner = owner; }
    public int[] getTracks() { return tracks; }
    public void setTracks(int[] tracks) { this.tracks = tracks; }
}
