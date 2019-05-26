package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.modelobjects.Playlist;
import me.yuri.oosedea.rest.dto.DTO;

import java.util.List;

public class PlaylistResponse implements DTO {

    private List<Playlist> playlists;

    public PlaylistResponse(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
