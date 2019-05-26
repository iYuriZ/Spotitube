package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.rest.dto.DTO;

import java.util.List;

public class TracksListResponse implements DTO {

    private List<Track> tracks;

    public TracksListResponse(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() { return tracks; }
}
