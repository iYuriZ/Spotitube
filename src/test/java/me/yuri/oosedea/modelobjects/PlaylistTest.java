package me.yuri.oosedea.modelobjects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistTest {

    private Playlist playlist1;
    private Playlist playlist2;

    @Before
    public void setup() {
        playlist1 = new Playlist();
        playlist2 = new Playlist();

        playlist1.setId(1);
        playlist1.setName("Rock");
        playlist1.setOwner(true);
        playlist1.setLength(0);
        playlist1.setTracks(new int[] {});

        playlist2.setId(1);
        playlist2.setName("Rock");
        playlist2.setOwner(true);
        playlist2.setLength(0);
        playlist2.setTracks(new int[] {});
    }

    @Test
    public void testEqualsReturnsTrue(){
        Assert.assertEquals(playlist1, playlist2);
    }
}
