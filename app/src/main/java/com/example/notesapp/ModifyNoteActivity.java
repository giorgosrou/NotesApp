package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    //Widgets
    private EditText titleModified;
    private Button updtBtn,dltBtn;
    private EditText descModified;

    private long _id;
    private DBmanager dBmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_note);

        dBmanager = new DBmanager(this);
        dBmanager.open();

        titleModified = findViewById(R.id.modify_title);
        descModified = findViewById(R.id.modify_description);

        updtBtn = findViewById(R.id.btn_update);
        dltBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id  = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        titleModified.setText(name);
        descModified.setText(desc);
        updtBtn.setOnClickListener(this);
        dltBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                String title = titleModified.getText().toString();
                String desc = descModified.getText().toString();
                dBmanager.update(_id,title,desc);
                this.returnHomepage();
                break;

            case  R.id.btn_delete:
                dBmanager.delete(_id);
                this.returnHomepage();
                break;
        }
    }

    public void returnHomepage() {
        Intent homeIntent = new Intent(getApplicationContext(), NotesListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}