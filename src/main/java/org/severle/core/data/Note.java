package org.severle.core.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class Note {
    private int startTick;
    private int playTime;
    private String lyric;
    private String phoneme;
    private int nativePitch;
}
