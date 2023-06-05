package org.severle.core.manager;

import org.severle.core.database.VocalDatabase;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DatabaseManager {
    private final Set<VocalDatabase> databases = new HashSet<>();
    private final VocalDatabase defaultDatabase = loadDefaultDatabase();
    private VocalDatabase loadDefaultDatabase() {
        return null;
    }

    public DatabaseManager() {
    }
    public DatabaseManager(Collection<VocalDatabase> databases) {
        this.databases.addAll(databases);
    }

    public VocalDatabase getDatabaseByUUID(UUID uuid) {
        for (VocalDatabase vocal : databases) {
            if (vocal.generateUUID().equals(uuid)) {
                return vocal;
            }
        }
        return null;
    }

    public boolean remove(VocalDatabase database) {
        return this.databases.remove(database);
    }

    public VocalDatabase getDefaultDatabase() {
        return this.defaultDatabase;
    }
}
