package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvDisplayResult);
        btnReturn = findViewById(R.id.btnReturn);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        tvResult.setText(result);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnIntent);
            }
        });
    }
}