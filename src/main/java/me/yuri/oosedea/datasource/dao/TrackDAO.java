package me.yuri.oosedea.datasource.dao;

import me.yuri.oosedea.datasource.DAOSetup;
import me.yuri.oosedea.modelobjects.Track;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DAOSetup {

    public List<Track> getAllTracksNotInPlaylist(int playlistId) {
        try {
            prepareStmt("SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable\n" +
                    "FROM tracks t WHERE t.id NOT IN (SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?);");
            stmt.setInt(1, playlistId);
            return getTracks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Track> getAllTracksInPlaylist(int playlistId) {
        try {
            prepareStmt("SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable\n" +
                    "FROM tracks t WHERE t.id IN (SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?);");
            stmt.setInt(1, playlistId);
            return getTracks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Track> getTracks() throws SQLException {
        List<Track> tracks = new ArrayList<>();
        ResultSet results = getResultSet();
        while (results.next()) {
            tracks.add(mapResultsToTrack(results));
        }
        return tracks;
    }

    private Track mapResultsToTrack(ResultSet results) throws SQLException {
        Track track = new Track();
        track.setId(results.getInt("id"));
        track.setTitle(results.getString("title"));
        track.setPerformer(results.getString("performer"));
        track.setDuration(results.getInt("duration"));
        track.setAlbum(results.getString("album"));
        track.setPlaycount(results.getInt("playcount"));
        track.setPublicationDate(results.getString("publicationDate"));
        track.setDescription(results.getString("description"));
        track.setOfflineAvailable(results.getBoolean("offlineAvailable"));
        return track;
    }

    public void addTrackToPlaylist(int playlistId, int trackId) {
        try {
            prepareStmt("INSERT INTO tracksInPlaylists (playlistId, trackId) VALUES (?, ?)");
            stmt.setInt(1, playlistId);
            stmt.setInt(2, trackId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try {
            prepareStmt("DELETE FROM tracksInPlaylists WHERE playlistId = ? AND trackId = ?");
            stmt.setInt(1, playlistId);
            stmt.setInt(2, trackId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTrackAvailability(int trackId, boolean offlineAvailable) {
        try {
            prepareStmt("UPDATE tracks SET offlineAvailable = ? WHERE id = ?");
            stmt.setBoolean(1, offlineAvailable);
            stmt.setInt(2, trackId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
