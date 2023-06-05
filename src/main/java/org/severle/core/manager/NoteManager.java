package org.severle.core.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.severle.core.data.Note;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class NoteManager {

    private final List<Note> notes = new ArrayList<>();



    public boolean addNote(Note note) {
        return this.notes.add(note);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Log4j2
    public static class NoteGroup {
        private Integer groupID;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NoteGroup) {
                return this.groupID.equals(((NoteGroup) obj).groupID);
            } else {
                return super.equals(obj);
            }
        }

        public static final NoteGroup NONE_GROUP = new NoteGroup(null);
    }
}
