package me.yuri.oosedea.datasource.dao;

import me.yuri.oosedea.datasource.mo.Playlist;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends DAOSetup {

    public List<Playlist> getPlaylistsIdByToken(String token) {
        List<Playlist> playlists = new ArrayList<Playlist>();
        try {
            prepareStmt("SELECT id, name FROM playlists WHERE owner_token = ?");
            ResultSet results = getResultSet();
            while (results.next()) {
                playlists.add(mapResultsToPlaylist(results.getInt("id"), results.getString("name")));
            }
            return playlists;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Playlist mapResultsToPlaylist(int id, String name) {
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setName(name);
        playlist.setOwner(true);
        playlist.setTracks(getTracksInPlaylist(id));
        return playlist;
    }

    private int[] getTracksInPlaylist(int id) {
        List<Integer> tracksInPlaylist = new ArrayList<Integer>();
        try {
            prepareStmt("SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?");
            ResultSet results = getResultSet();
            while (results.next()) {
                tracksInPlaylist.add(results.getInt("trackId"));
            }
            int[] tracks = tracksInPlaylist.stream().mapToInt(i->i).toArray();
            return tracks;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
