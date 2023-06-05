package org.severle.core.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.severle.core.data.Track;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class TrackManager {
    private List<Track> tracks;

    public boolean addTrack(Track track) {
        return this.tracks.add(track);
    }

    public boolean addTrack(UUID databaseUUID) {
        return this.tracks.add(new Track(this.tracks.size() - 1, databaseUUID));
    }
}
