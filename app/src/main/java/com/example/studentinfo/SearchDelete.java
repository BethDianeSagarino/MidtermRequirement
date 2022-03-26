package com.example.studentinfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchDelete extends AppCompatActivity {
    EditText id;
    Button deleteData, searchData, viewAllData, backData;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_delete);
        id = findViewById(R.id.studIDTXT);

        deleteData = findViewById(R.id.deletesearchDataButton);
        searchData = findViewById(R.id.searchDataButton);
        viewAllData = findViewById(R.id.viewAllDataButton);
        backData = findViewById(R.id.backButton);

        DB = new DBHelper(this);

        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = id.getText().toString();
                SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("Userdata.db", Context.MODE_PRIVATE,null);
                Cursor res = DB.rawQuery("Select * from Userdetails where id ='"+input+"'", null);
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(),"No Record",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Gender :"+res.getString(2)+"\n");
                    buffer.append("Address :"+res.getString(3)+"\n");
                    buffer.append("Age :"+res.getString(4)+"\n");
                    buffer.append("Year :"+res.getString(5)+"\n");
                    buffer.append("Course :"+res.getString(6)+"\n\n");
                }
                Toast.makeText(getApplicationContext(),"Search by ID Result \n\n"+buffer.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = id.getText().toString();
                SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("Userdata.db", Context.MODE_PRIVATE,null);
                DB.execSQL("delete from Userdetails where id ='"+input+"'");

                Toast.makeText(SearchDelete.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();

            }
        });

        viewAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(SearchDelete.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Gender :"+res.getString(2)+"\n");
                    buffer.append("Address :"+res.getString(3)+"\n");
                    buffer.append("Age :"+res.getString(4)+"\n");
                    buffer.append("Year :"+res.getString(5)+"\n");
                    buffer.append("Course :"+res.getString(6)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchDelete.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
        backData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}