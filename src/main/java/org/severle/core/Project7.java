package org.severle.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.severle.core.manager.DatabaseManager;
import org.severle.core.manager.MasterManager;
import org.severle.core.manager.TrackManager;


@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class Project7 {
    private long createTime;
    private long lastSaveTime;
    private String version;
    private MasterManager masterManager;
    private DatabaseManager databaseManager;
    private TrackManager trackManager;
}
