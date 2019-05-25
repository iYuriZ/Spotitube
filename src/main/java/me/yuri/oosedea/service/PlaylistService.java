package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.datasource.mo.Playlist;
import me.yuri.oosedea.rest.dto.implementation.PlaylistResponse;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService {

    public PlaylistService() {}

    @Inject
    private PlaylistDAO playlistDAO;

    public List<Playlist> getAllPlaylists(String token) {
        return playlistDAO.findAllPlaylistsByToken(token);
    }

    public List<Playlist> addNewPlaylist(String token, String name) {
        playlistDAO.addNewPlaylistOnToken(token, name);
        return this.getAllPlaylists(token);
    }

    public List<Playlist> renamePlaylist(int id, String token, String name) {
        playlistDAO.updatePlaylistOnToken(id, token, name);
        return this.getAllPlaylists(token);
    }

    public List<Playlist> deletePlaylist(int id, String token) {
        playlistDAO.deletePlaylistOnToken(id, token);
        return this.getAllPlaylists(token);
    }
}
