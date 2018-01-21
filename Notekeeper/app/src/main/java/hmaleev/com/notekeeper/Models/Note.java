package hmaleev.com.notekeeper.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.List;

/**
 * Created by Xristo on 21.1.2018 г..
 */

@Entity
public  final class Note {

        @PrimaryKey(autoGenerate = true)
        private int noteUid;

        @ColumnInfo(name = "title")
        private String title;

        @ColumnInfo(name = "content")
        private String content;

        public int getNoteUid() {
                return noteUid;
        }

        public void setNoteUid(int uid) {
                this.noteUid = uid;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content){
                this.content = content;
        }

}