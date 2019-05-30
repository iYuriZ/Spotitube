package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.modelobjects.Playlist;
import me.yuri.oosedea.rest.dto.DTO;

import java.util.List;

public class PlaylistResponse implements DTO {

    private List<Playlist> playlists;
    private int length;

    public PlaylistResponse(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public int getLength() { return length; }
}
