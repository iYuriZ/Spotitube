package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.datasource.mo.Playlist;
import me.yuri.oosedea.rest.dto.PlaylistResponse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    public PlaylistService() {}

    @Inject
    private PlaylistDAO playlistDAO;

    public PlaylistResponse getPlaylists(String token) {
        PlaylistResponse response = new PlaylistResponse();
        return getPlaylists(token);
    }

}
