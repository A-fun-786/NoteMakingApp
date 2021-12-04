package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private NoteViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;

    private TextView textView;
    private FloatingActionButton addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        mAdapter = new NoteAdapter(new NoteAdapter.NoteDiff());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.clickListener(new NoteClickListener() {
            @Override
            public void onClick(Note note,int index) {
                if(index == 0)
                    mViewModel.delete(note);
                if(index == 1) {
                    Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                    intent.putExtra("currentNote",note);
                    System.out.println("Initial note id "+note.id+" "+note.mdescription);
                    startActivityForResult(intent,1000);
                }
            }
        });
        mViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mViewModel.getAllNotes().observe(this,(notes) ->{
            mAdapter.submitList(notes);
        });

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener((view)->{
            noteActivityOpener();
        });

    }

    public void noteActivityOpener() {
        Intent intent = new Intent(this,NoteActivity.class);
        startActivityForResult(intent,999);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Note note = (Note) data.getSerializableExtra("note");
        if(note.mtitle.equals("") && note.mdescription.equals("")) {
            return;
        }

        if (requestCode == 999) {
            mViewModel.insert(note);
        }else if(requestCode == 1000){
            System.out.println("updated note id "+note.id+" "+note.mdescription);
            mViewModel.update(note);
            } else{
                Toast.makeText(
                        getApplicationContext(),
                        "Text not saved",
                        Toast.LENGTH_LONG).show();
            }
    }

}