package com.example.ex1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText num1TextView, num2TextView;
    TextView resultTextView;
    Spinner spinner;
    Button calcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1TextView = findViewById(R.id.num1TextView);
        num2TextView = findViewById(R.id.num2TextView);
        resultTextView = findViewById(R.id.resultTextView);
        spinner =  (Spinner) findViewById(R.id.spinner);
        calcBtn = findViewById(R.id.calcBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Operators_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        calcBtn.setOnClickListener(btnClick());

    }

    View.OnClickListener btnClick () {
        return v -> {
            try {
                int number1 = Integer.parseInt(num1TextView.getText().toString());
                int number2 = Integer.parseInt(num2TextView.getText().toString());
                String operation = spinner.getSelectedItem().toString();
                double result = 0;

                switch (operation) {
                    case "+":
                        result = number1 + number2;
                        break;
                    case "-":
                        result = number1 - number2;
                        break;
                    case "*":
                        result = number1 * number2;
                        break;
                    case "/":
                        if(number2 == 0)
                            throw new Exception("division by zero");
                        result = number1 / number2;
                        break;
                    case "^":
                        result = Math.pow(number1, number2);
                        break;
                }

                resultTextView.setText(String.valueOf(result));
            }
            catch (Exception e) {
                resultTextView.setText("Error: " + e.getMessage());
            }
        };
    };


}