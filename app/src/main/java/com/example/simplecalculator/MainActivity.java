package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // defining variables for the  view components
    private EditText etNum1, etNum2;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // linking our variables to the views in the layout
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtact);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        tvResult = findViewById(R.id.tvResult);

        // defining click listeners
        btnAdd.setOnClickListener(v -> calculateResult('+'));

        btnSubtract.setOnClickListener(v -> calculateResult('-'));

        btnMultiply.setOnClickListener(v -> calculateResult('*'));

        btnDivide.setOnClickListener(v -> calculateResult('/'));
    }

    private void calculateResult(char operator) {
        // fetching the inputs
        String input1 = etNum1.getText().toString().trim();
        String input2 = etNum2.getText().toString().trim();

        // checking for empty inputs
        if(input1.isEmpty() || input2.isEmpty()) {
            tvResult.setText(null);
            Toast.makeText(this, "Please enter the operands !!", Toast.LENGTH_SHORT).show();
            return;
        }

        // getting the operands
        int num1 = Integer.parseInt(input1);
        int num2 = Integer.parseInt(input2);

        String result = null;

        // perform calculation and set result
        switch (operator) {
            case '+':
                //tvResult.setText("Sum = " + (num1 + num2));
                result = "Sum = " + (num1 + num2);
                break;
            case '-':
                //tvResult.setText("Difference = " + (num1 - num2));
                result = "Difference = " + (num1 - num2);
                break;
            case '*':
                //tvResult.setText("Product = " + (num1 * num2));
                result = "Product = " + (num1 * num2);
                break;
            case '/':
                if(num2 == 0)
                    result = "Undefined Result";
                    //tvResult.setText("Undefined Result");
                else
                    result = String.format("Quotient = %.2f", (float)num1 / num2);
                    //tvResult.setText(String.format("Quotient = %.2f", (float)num1 / num2));
        }

        // move to result activity
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}