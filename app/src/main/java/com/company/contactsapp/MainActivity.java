package com.company.contactsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list1);

        button=findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        db=new DatabaseHelper(MainActivity.this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact= (Contact) listView.getAdapter().getItem(i);
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("name",contact.getName());
                intent.putExtra("phone",contact.getPhone());
                intent.putExtra("id",contact.getId());
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Contact> contacts=db.getContacts();
        ContactAdapter adapter=new ContactAdapter(this,contacts);
        listView.setAdapter(adapter);
    }
}
