package com.company.contactsapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Button button;
    int id;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit1=findViewById(R.id.editname);
        edit2=findViewById(R.id.editphone);
        button=findViewById(R.id.btn);

        id=getIntent().getIntExtra("id",0);


        edit1.setText(getIntent().getStringExtra("name"));
        edit2.setText(getIntent().getStringExtra("phone"));

        db=new DatabaseHelper(UpdateActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact=new Contact(id,edit1.getText().toString().trim(),edit2.getText().toString().trim());
                db.updateContact(contact);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to delete this contact ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteContact(id);
                                finish();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}






















// CRUD   create read   update  delete   








