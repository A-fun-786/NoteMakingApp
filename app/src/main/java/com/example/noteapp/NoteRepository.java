package com.example.noteapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    LiveData<List<Note>> mallNotes;
    NoteDao mnoteDao;

    NoteRepository(Application application){
        NotesDB db = NotesDB.getDatabase(application);
        mnoteDao = db.noteDao();
        mallNotes = mnoteDao.getAllNotes();
    }

    void insert(Note note){
       NotesDB.databaseWriteExecutor.execute(() -> {
            mnoteDao.insert(note);
        });
    }

    void delete(Note note){
        NotesDB.databaseWriteExecutor.execute(() -> {
            mnoteDao.delete(note);
        });
    }

    void update(Note note){
        NotesDB.databaseWriteExecutor.execute(() -> {
            mnoteDao.update(note);
        });
    }

    LiveData<List<Note>> getallNotes(){
        return mallNotes;
    }
}
