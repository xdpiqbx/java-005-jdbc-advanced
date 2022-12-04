package cw.jdbcadv.feature.storage;

import cw.jdbcadv.feature.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public void initDb(Storage storage){
        String initDbFilename = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);
        try {
            String sql = String.join("\n", Files.readAllLines(Paths.get(initDbFilename)));
            storage.executeUpdate(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
