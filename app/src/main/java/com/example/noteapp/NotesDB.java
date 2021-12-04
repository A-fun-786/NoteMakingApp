package com.example.noteapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NotesDB extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile NotesDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NotesDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotesDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDB.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}