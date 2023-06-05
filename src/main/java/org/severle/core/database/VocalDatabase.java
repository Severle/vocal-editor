package org.severle.core.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log4j2
public abstract class VocalDatabase {
    private String vocalName;
    private String vocalVersion;

    public UUID generateUUID() {
        return UUID.fromString(this.vocalName + this.vocalVersion);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VocalDatabase) {
            return this.generateUUID().equals(((VocalDatabase) obj).generateUUID());
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vocalName, this.vocalVersion);
    }
}
