package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "note_table")
public class Note implements Serializable {

    @NonNull
    @ColumnInfo(name = "title")
    String mtitle;

    @NonNull
    @ColumnInfo(name = "description")
    String mdescription;

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Note(@NonNull String title,@NonNull String description){
        mtitle = title;
        mdescription = description;
    }

    public Note() {

    }

    public String getMtitle() {
        return this.mtitle;
    }

    public String getMdescription() {
        return this.mdescription;
    }
}
