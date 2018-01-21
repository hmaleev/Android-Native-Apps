package hmaleev.com.notekeeper.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Xristo on 21.1.2018 г..
 */


@Entity
public  final class Tag {

    @PrimaryKey(autoGenerate = true)
    private int tagUid;

    @ColumnInfo(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTagUid() {
        return tagUid;
    }
    public void setTagUid(int tagUid){
        this.tagUid = tagUid;
    }

}
