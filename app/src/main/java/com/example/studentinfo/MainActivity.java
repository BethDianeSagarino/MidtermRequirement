package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, gender, address, age, year, course;
    Button insertData, updateData, deleteData, viewData, searchData;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name_text);
        gender = findViewById(R.id.gender_text);
        address = findViewById(R.id.address_text);
        age = findViewById(R.id.age_text);
        year = findViewById(R.id.year_text);
        course = findViewById(R.id.course_text);

        insertData = findViewById(R.id.insertDataButton);
        updateData = findViewById(R.id.updateDataButton);
        viewData = findViewById(R.id.viewDataButton);
        searchData = findViewById(R.id.searchDataButton);



        DB = new DBHelper(this);
        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String genderTXT = gender.getText().toString();
                String addressTXT = address.getText().toString();
                String ageTXT = age.getText().toString();
                String yearTXT = year.getText().toString();
                String courseTXT = course.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, genderTXT, addressTXT, ageTXT, yearTXT, courseTXT);
                if(checkinsertdata)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String genderTXT = gender.getText().toString();
                String addressTXT = address.getText().toString();
                String ageTXT = age.getText().toString();
                String yearTXT = year.getText().toString();
                String courseTXT = course.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, genderTXT, addressTXT, ageTXT, yearTXT, courseTXT);

                if(checkupdatedata)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });


        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchDelete.class);
                startActivity(i);
            }
        });


        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });


    }}