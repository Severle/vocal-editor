package org.severle.core.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.severle.core.manager.ControlPointManager;
import org.severle.core.manager.NoteManager;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class Track {
    private int id;
    private String name;
    private String comment;
    private UUID databaseUUID;
    private NoteManager noteManager;
    private ControlPointManager pointManager;
    private boolean isMute;
    private int volume;

    public Track(int id, UUID databaseUUID) {
        this.id = id;
        this.name = "New Track";
        this.comment = "";
        this.databaseUUID = databaseUUID;
        this.noteManager = new NoteManager();
        this.pointManager = new ControlPointManager();
        this.isMute = false;
        this.volume = 100;
    }

    public Track(UUID databaseUUID) {
        this(0, databaseUUID);
    }
}
