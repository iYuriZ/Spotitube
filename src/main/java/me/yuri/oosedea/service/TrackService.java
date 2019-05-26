package me.yuri.oosedea.service;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Track;

import java.util.List;

public interface TrackService {

    List<Track> getAllTracksInPlaylist(int playlistId, String token, boolean authorized) throws UnauthorizedUserException;

    List<Track> getAllTracksNotInPlaylist(int playlistId, String token) throws UnauthorizedUserException;

    List<Track> addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable, String token) throws UnauthorizedUserException;

    List<Track> removeTrackFromPlaylist(int playlistId, int trackId, String token) throws UnauthorizedUserException;

}
