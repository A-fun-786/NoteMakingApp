package com.example.noteapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    public NoteAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback) {
        super(diffCallback);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NoteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note current = getItem(position);
        holder.bind(current);
    }

    static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getMtitle().equals(newItem.getMtitle());
        }
    }

    static NoteClickListener listener;
    public void clickListener(NoteClickListener listener){
        this.listener = listener;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private FloatingActionButton deleteButton;
        private final View itemView;

        private NoteViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.textView);
            description = view.findViewById(R.id.textView2);
            itemView = view;

            deleteButton = view.findViewById(R.id.deleteButton);
            deleteButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        }

        public void bind(Note note) {
            title.setText(note.mtitle);
            description.setText(note.mdescription);

            deleteButton.setOnClickListener((v)->{
                System.out.println("clicked");
                listener.onClick(note,0);
            });

            itemView.setOnClickListener((v)->{
                listener.onClick(note,1);
            });
        }

        static NoteViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_item, parent, false);
            return new NoteViewHolder(view);
        }
    }

}
interface NoteClickListener{
    void onClick(Note note,int index);
}
