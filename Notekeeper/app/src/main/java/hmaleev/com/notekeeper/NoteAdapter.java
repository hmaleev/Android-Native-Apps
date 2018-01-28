package hmaleev.com.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hmaleev.com.notekeeper.Models.Note;

/**
 * Created by Hristo on 23.1.2018 г..
 */
public class NoteAdapter extends
        RecyclerView.Adapter<NoteAdapter.ViewHolder>  {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title;
        public TextView noteContent;
        private Context context;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context,  View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.noteListTitle);
            noteContent = (TextView) itemView.findViewById(R.id.noteListContent);
            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Note note = notes.get(position);
                Intent editIntent = new Intent(context, EditNote.class);
                editIntent.putExtra("title",note.getTitle());
                editIntent.putExtra("content",note.getContent());
                editIntent.putExtra("uid",note.getNoteUid());
                context.startActivity(editIntent);
            }
        }

    }

    private List<Note> notes;
    private Context mContext;

    public NoteAdapter(Context context, List<Note> notesList) {
        notes = notesList;
        mContext = context;
    }
    private Context getContext() {
        return mContext;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.recycler_view_main_template, parent, false);

        ViewHolder viewHolder = new ViewHolder(getContext(), contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Note contact = notes.get(position);

        TextView title = viewHolder.title;
        title.setText(contact.getTitle());
        TextView content = viewHolder.noteContent;
        content.setText(contact.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}