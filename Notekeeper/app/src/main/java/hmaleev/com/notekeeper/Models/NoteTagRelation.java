package hmaleev.com.notekeeper.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
/**
 * Created by Xristo on 21.1.2018 г..
 */

@Entity
public class NoteTagRelation {

    @PrimaryKey
    private int tagUid;

    @PrimaryKey
    private int noteUid;

}
