package com.assignment;


import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecentlyPlayedSongsStoreTest {
    private RecentlyPlayedSongsStore store;

    @BeforeMethod
    public void setup() {
        store = new RecentlyPlayedSongsStore(3);
    }

    @Test
    public void testAddRecentlyPlayedSongs() {
        store.addRecentlyPlayedSong("user1", "S1");
        store.addRecentlyPlayedSong("user1", "S2");
        store.addRecentlyPlayedSong("user1", "S3");

        List<String> expectedSongs = Arrays.asList("S1", "S2", "S3");
        List<String> actualSongs = store.getRecentlyPlayedSongs("user1");
        Assert.assertEquals(actualSongs, expectedSongs);

        store.addRecentlyPlayedSong("user1", "S4");

        expectedSongs = Arrays.asList("S2", "S3", "S4");
        actualSongs = store.getRecentlyPlayedSongs("user1");
        Assert.assertEquals(actualSongs, expectedSongs);

        store.addRecentlyPlayedSong("user1", "S2");

        expectedSongs = Arrays.asList("S3", "S4", "S2");
        actualSongs = store.getRecentlyPlayedSongs("user1");
        Assert.assertEquals(actualSongs, expectedSongs);

        store.addRecentlyPlayedSong("user1", "S1");

        expectedSongs = Arrays.asList("S4", "S2", "S1");
        actualSongs = store.getRecentlyPlayedSongs("user1");
        Assert.assertEquals(actualSongs, expectedSongs);
    }

    @Test
    public void testGetRecentlyPlayedSongsNonExistentUser() {
        List<String> songs = store.getRecentlyPlayedSongs("non_existent_user");
        Assert.assertTrue(songs.isEmpty());
    }
}
