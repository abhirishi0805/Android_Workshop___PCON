package com.example.attendance_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewSubjectActivity extends AppCompatActivity {
EditText editText,editText2,editText3,editText4;
Button save_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        save_button=findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject=editText.getText().toString();
                String totalclassstring = editText2.getText().toString();
                String totalpresentstring= editText3.getText().toString();
                String minumumstring = editText4.getText().toString();
                if(subject.isEmpty() || totalclassstring.isEmpty()||totalpresentstring.isEmpty()|| minumumstring.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill all the field !",Toast.LENGTH_SHORT).show();
                }
                else{
                    int totalclass = Integer.parseInt(totalclassstring);
                    int totalpresent = Integer.parseInt(totalpresentstring);
                    int minimum = Integer.parseInt(minumumstring);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Subject");
                    String id = reference.push().getKey();
                    Subject s = new Subject(id,subject,totalclass,totalpresent,minimum);
                    reference.child(id).setValue(s);
                    Toast.makeText(getApplicationContext(),"New Subject added",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}