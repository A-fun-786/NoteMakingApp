package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class NoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button saveButton;
    private boolean updated = false;
    private Intent mintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        title = findViewById(R.id.titleText);
        description = findViewById(R.id.descriptionText);
        saveButton = findViewById(R.id.save);

        Note currentNote = (Note)getIntent().getSerializableExtra("currentNote");
        if(currentNote != null){
            Note note = (Note)getIntent().getSerializableExtra("currentNote");
            title.setText(note.mtitle);
            description.setText(note.mdescription);
            updated = true;
        }

        Note note = new Note(title.getText().toString(), description.getText().toString());
        note.id = (currentNote != null) ? currentNote.id : note.id;

        mintent = new Intent();
        mintent.putExtra("note", note);

        saveButton.setOnClickListener((view) -> {

            if(updated){ setResult(1000,mintent); }
            else { setResult(999, mintent); }

            finish();
        });

    }
    @Override
    public void onBackPressed() {
        // your code.
        setResult(-1,mintent);
        finish();
    }
}