package com.company.contactsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Button button;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edit1=findViewById(R.id.editname);
        edit2=findViewById(R.id.editphone);
        button=findViewById(R.id.btn);

        db=new DatabaseHelper(AddActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().toString().trim().length()>0&&edit2.getText().toString().trim().length()>0) {
                    Contact contact = new Contact(edit1.getText().toString(), edit2.getText().toString());
                    db.addContact(contact);
                    Toast.makeText(AddActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
