package com.assignment;
import java.util.*;

public class RecentlyPlayedSongsStore {
    private final int initialCapacity;
    private final HashMap<String, Queue<String>> userSongsMap;

    public RecentlyPlayedSongsStore(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.userSongsMap = new HashMap<>();
    }

    public void addRecentlyPlayedSong(String user, String song) {
        if (!userSongsMap.containsKey(user)) {
            userSongsMap.put(user, new LinkedList<>());
        }

        Queue<String> songsQueue = userSongsMap.get(user);

        // Remove the song from the queue if it already exists, so it can be added to the front
        songsQueue.remove(song);
        songsQueue.add(song);

        // If the queue size exceeds the initial capacity, remove the least recently played song
        if (songsQueue.size() > initialCapacity) {
            songsQueue.poll();
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        if (!userSongsMap.containsKey(user)) {
            return Collections.emptyList();
        }

        // Return a copy of the queue to avoid external modification
        return new ArrayList<>(userSongsMap.get(user));
    }
}
