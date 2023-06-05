package org.severle.core.factory;

import org.severle.core.Project7;
import org.severle.core.data.Beat;
import org.severle.core.manager.DatabaseManager;
import org.severle.core.manager.MasterManager;
import org.severle.core.manager.TrackManager;

public class ProjectFactory {
    private static String getVersion() {
        return "1.0.0";
    }
    public static Project7 createBlankProject() {
        Project7 project7 = new Project7();
        project7.setCreateTime(System.currentTimeMillis());
        project7.setLastSaveTime(System.currentTimeMillis());
        project7.setVersion(getVersion());
        project7.setMasterManager(getDefaultMasterManager());
        project7.setDatabaseManager(getDefaultDatabaseManager());
        project7.setTrackManager(getDefaultTrackManager());
        return project7;
    }

    public static MasterManager getDefaultMasterManager() {
        MasterManager manager = new MasterManager();
        manager.setMainBeat(new Beat(12000));
        return manager;
    }

    public static DatabaseManager getDefaultDatabaseManager() {
        return new DatabaseManager();
    }

    public static TrackManager getDefaultTrackManager() {
        return new TrackManager();
    }
}
