package hmaleev.com.notekeeper.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import hmaleev.com.notekeeper.Models.Note;

/**
 * Created by Xristo on 21.1.2018 г..
 */
@Dao
public interface  NoteDao {

        @Query("SELECT * FROM Note")
        List<Note> getAll();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Note... note);

        @Delete
        void delete(Note... note);

        @Update
        void update (Note... note);

}