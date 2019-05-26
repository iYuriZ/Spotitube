package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Playlist;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService {

    public PlaylistService() {}

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private LoginService loginService;

    public List<Playlist> getAllPlaylists(String token, boolean authorized) throws UnauthorizedUserException {
        if(!authorized) {
            loginService.authorizeByToken(token);
        }
        return playlistDAO.findAllPlaylistsByToken(token);
    }

    public List<Playlist> addNewPlaylist(String token, String name) throws UnauthorizedUserException {
        playlistDAO.addNewPlaylistOnToken(token, name);
        return this.getAllPlaylists(token, true);
    }

    public List<Playlist> renamePlaylist(int id, String token, String name) throws UnauthorizedUserException {
        playlistDAO.updatePlaylistOnToken(id, token, name);
        return this.getAllPlaylists(token, true);
    }

    public List<Playlist> deletePlaylist(int id, String token) throws UnauthorizedUserException {
        playlistDAO.deletePlaylistOnToken(id, token);
        return this.getAllPlaylists(token, true);
    }
}
