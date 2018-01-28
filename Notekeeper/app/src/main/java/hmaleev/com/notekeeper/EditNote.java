package hmaleev.com.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hmaleev.com.notekeeper.Models.Note;

public class EditNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        ((TextView)findViewById(R.id.editNoteTitle)).setText(intent.getStringExtra("title"));
        ((TextView)findViewById(R.id.editNoteContent)).setText(intent.getStringExtra("content"));

        FloatingActionButton editBtn = (FloatingActionButton) findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = AppDatabase.getAppDatabase(EditNote.this);

                EditText noteTitleField = (EditText)findViewById(R.id.editNoteTitle);
                String title = noteTitleField.getText().toString();
                EditText noteContentField = (EditText)findViewById(R.id.editNoteContent);
                String content = noteContentField.getText().toString();
                int noteUid = intent.getIntExtra("uid",-1);
                if(noteUid != -1){ // if noteUid is -1 the note doesn't exist
                    Note note = new Note();
                    note.setNoteUid(noteUid);
                    note.setContent(content);
                    note.setTitle(title);
                    db.noteDao().update(note);
                    Log.i("INFO", "Note Updated");
                }

                Intent intent = new Intent(EditNote.this, MainActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
