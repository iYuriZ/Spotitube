package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PlaylistService {

    public PlaylistService() {}

    @Autowired
    private PlaylistDAO playlistDAO;

    @Autowired
    private LoginService loginService;

    public List<Playlist> getAllPlaylists(String token, boolean authorized) throws UnauthorizedUserException {
        if(!authorized) {
            loginService.authorizeByToken(token);
        }
        return playlistDAO.findAllPlaylists(token);
    }

    public int getTotalLength(List<Playlist> playlists) {
        int totalLength = 0;
        for (Playlist playlist : playlists) {
            totalLength += playlist.getLength();
        }
        return totalLength;
    }

    public List<Playlist> addNewPlaylist(String token, String name) throws UnauthorizedUserException {
        playlistDAO.addNewPlaylist(token, name);
        return this.getAllPlaylists(token, true);
    }

    public List<Playlist> renamePlaylist(int id, String token, String name) throws UnauthorizedUserException {
        playlistDAO.updatePlaylist(id, token, name);
        return this.getAllPlaylists(token, true);
    }

    public List<Playlist> deletePlaylist(int id, String token) throws UnauthorizedUserException {
        playlistDAO.deletePlaylist(id, token);
        return this.getAllPlaylists(token, true);
    }
}
