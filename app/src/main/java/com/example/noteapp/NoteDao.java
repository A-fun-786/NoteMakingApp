package com.example.noteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    public void insert(Note note);

    @Delete
    public void delete(Note note);

    @Update
    public void update(Note note);

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    public LiveData<List<Note>> getAllNotes();

}
