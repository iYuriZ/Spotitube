package me.yuri.oosedea.service.implementation;

import me.yuri.oosedea.datasource.dao.TrackDAO;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.service.LoginService;
import me.yuri.oosedea.service.TrackService;

import javax.inject.Inject;
import java.util.List;

public class TrackServiceImpl implements TrackService {

    @Inject
    private TrackDAO trackDAO;

    @Inject
    private LoginService loginService;

    @Override
    public List<Track> getAllTracksInPlaylist(int playlistId, String token, boolean authorized) throws UnauthorizedUserException {
        if (!authorized) {
            loginService.authorizeByToken(token);
        }
        return trackDAO.getAllTracksInPlaylist(playlistId);
    }

    @Override
    public List<Track> getAllTracksNotInPlaylist(int playlistId, String token) throws UnauthorizedUserException {
        loginService.authorizeByToken(token);
        return trackDAO.getAllTracksNotInPlaylist(playlistId);
    }

    @Override
    public List<Track> addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable, String token) throws UnauthorizedUserException {
        loginService.authorizeByToken(token);
        trackDAO.updateTrackAvailability(trackId, offlineAvailable);
        trackDAO.addTrackToPlaylist(playlistId, trackId);
        return this.getAllTracksInPlaylist(playlistId, token, true);
    }

    @Override
    public List<Track> removeTrackFromPlaylist(int playlistId, int trackId, String token) throws UnauthorizedUserException {
        loginService.authorizeByToken(token);
        trackDAO.removeTrackFromPlaylist(playlistId, trackId);
        return this.getAllTracksInPlaylist(playlistId, token, true);
    }
}
