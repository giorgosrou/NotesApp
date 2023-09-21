package com.example.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity implements View.OnClickListener {

    //Widgets
    private Button addNoteBtn;
    private EditText addTitle;
    private EditText addDescription;
    private DBmanager dBmanager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_note);

        //Initialise the states
        addTitle = findViewById(R.id.add_title);
        addDescription = findViewById(R.id.add_description);
        addNoteBtn = findViewById(R.id.add_record);

        dBmanager = new DBmanager(this);
        dBmanager.open();
        addNoteBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_record:
                final String name = addTitle.getText().toString();
                final String desc = addDescription.getText().toString();

                dBmanager.insert(name,desc);

                Intent  main = new Intent(AddNoteActivity.this, NotesListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}