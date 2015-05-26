package evertonrmachado.gclientes.backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * Created by Everton on 19/05/2015.
 */
public class Backup extends BackupAgentHelper {

    // O nome do arquivo SharedPreferences
    static final String banco = "GClientes";

    // A chave para identificar o conjunto de dados de backup
    static final String PREFS_BACKUP_KEY = "myfiles";

    public void onCreate() {
        FileBackupHelper helper = new FileBackupHelper(this, banco);
        addHelper(PREFS_BACKUP_KEY, helper);
    }
}