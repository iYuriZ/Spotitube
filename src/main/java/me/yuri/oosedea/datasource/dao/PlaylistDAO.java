package me.yuri.oosedea.datasource.dao;

import me.yuri.oosedea.datasource.DAOSetup;
import me.yuri.oosedea.modelobjects.Playlist;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends DAOSetup {

    public List<Playlist> findAllPlaylistsByToken(String token) {
        try {
            prepareStmt("SELECT id, name FROM playlists WHERE owner_token = ?");
            stmt.setString(1, token);
            return getListOfPlaylists();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Playlist> getListOfPlaylists() throws SQLException {
        List<Playlist> playlists = new ArrayList<Playlist>();
        ResultSet results = getResultSet();
        while (results.next()) {
            playlists.add(mapResultsToPlaylist(results.getInt("id"), results.getString("name")));
        }
        return playlists;
    }

    private Playlist mapResultsToPlaylist(int playlistId, String name) {
        Playlist playlist = new Playlist();
        playlist.setId(playlistId);
        playlist.setName(name);
        playlist.setOwner(true);
        playlist.setTracks(getTracksInPlaylist(playlistId));
        return playlist;
    }

    private int[] getTracksInPlaylist(int playlistId) {
        try {
            prepareStmt("SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?");
            stmt.setInt(1, playlistId);
            return getAllTrackIdsInPlaylist();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int[] getAllTrackIdsInPlaylist() throws SQLException {
        List<Integer> tracksInPlaylist = new ArrayList<Integer>();
        ResultSet results = getResultSet();
        while (results.next()) {
            tracksInPlaylist.add(results.getInt("trackId"));
        }
        return tracksInPlaylist.stream().mapToInt(i->i).toArray();
    }

    public void addNewPlaylistOnToken(String token, String name) {
        try {
            prepareStmt("INSERT INTO playlists (name, owner_token, length) VALUES (?, ?, 0)");
            stmt.setString(1, name);
            stmt.setString(2, token);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePlaylistOnToken(int id, String token, String name) {
        try {
            prepareStmt("UPDATE playlists SET name = ? WHERE id = ? AND owner_token = ?");
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.setString(3, token);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePlaylistOnToken(int id, String token) {
        try {
            prepareStmt("DELETE FROM playlists WHERE id = ? AND owner_token = ?");
            stmt.setInt(1, id);
            stmt.setString(2, token);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
