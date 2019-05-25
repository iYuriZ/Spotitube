package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.TrackDAO;
import me.yuri.oosedea.datasource.mo.Track;

import javax.inject.Inject;
import java.util.List;

public class TracksService {

    @Inject
    private TrackDAO trackDAO;

    public List<Track> getAllTracksInPlaylist(int playlistId) {
        return trackDAO.getAllTracksInPlaylist(playlistId);
    }

    public List<Track> getAllTracksNotInPlaylist(int playlistId) {
        return trackDAO.getAllTracksNotInPlaylist(playlistId);
    }

    public List<Track> addTrackToPlaylist(int playlistId, int trackId) {
        trackDAO.addTrackToPlaylist(playlistId, trackId);
        return this.getAllTracksInPlaylist(playlistId);
    }

    public List<Track> removeTrackFromPlaylist(int playlistId, int trackId) {
        trackDAO.removeTrackFromPlaylist(playlistId, trackId);
        return this.getAllTracksInPlaylist(playlistId);
    }
}
