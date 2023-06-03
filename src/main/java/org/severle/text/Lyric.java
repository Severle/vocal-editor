package org.severle.text;

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
public class Lyric {
    private String lyric;
    private String phoneme;

    public void setData(String lyric, String phoneme) {
        this.lyric = lyric;
        this.phoneme = phoneme;
    }
}
