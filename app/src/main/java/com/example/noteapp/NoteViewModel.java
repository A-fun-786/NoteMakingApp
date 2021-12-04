package com.example.noteapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    NoteRepository mNoteRepository;
    LiveData<List<Note>> mallNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNoteRepository = new NoteRepository(application);
        mallNotes = mNoteRepository.getallNotes();
    }

    LiveData<List<Note>> getAllNotes(){ return mallNotes; }

    void insert(Note note){
        mNoteRepository.insert(note);
    }
    void delete(Note note){
        mNoteRepository.delete(note);
    }
    void update(Note note) { mNoteRepository.update(note); }
}
