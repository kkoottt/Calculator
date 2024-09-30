package com.example.cocomeloncalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cocomeloncalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText num1EditText;
    private EditText num2EditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize views
        num1EditText = findViewById(R.id.num1);
        num2EditText = findViewById(R.id.num2);
        resultTextView = findViewById(R.id.resultTextView);
        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button divideButton = findViewById(R.id.divideButton);

        // Set click listeners for buttons
        addButton.setOnClickListener(v -> addNumbers());
        subtractButton.setOnClickListener(v -> subtractNumbers());
        multiplyButton.setOnClickListener(v -> multiplyNumbers());
        divideButton.setOnClickListener(v -> divideNumbers());
    }

    private void addNumbers() {
        performOperation(Operation.ADD);
    }

    private void subtractNumbers() {
        performOperation(Operation.SUBTRACT);
    }

    private void multiplyNumbers() {
        performOperation(Operation.MULTIPLY);
    }

    private void divideNumbers() {
        performOperation(Operation.DIVIDE);
    }

    private void performOperation(Operation operation) {
        String num1String = num1EditText.getText().toString();
        String num2String = num2EditText.getText().toString();

        // Check if inputs are not empty
        if (!num1String.isEmpty() && !num2String.isEmpty()) {
            double num1 = Double.parseDouble(num1String);
            double num2 = Double.parseDouble(num2String);
            double result;

            switch (operation) {
                case ADD:
                    result = num1 + num2;
                    break;
                case SUBTRACT:
                    result = num1 - num2;
                    break;
                case MULTIPLY:
                    result = num1 * num2;
                    break;
                case DIVIDE:
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultTextView.setText("Cannot divide by zero.");
                        return;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected operation: " + operation);
            }
            resultTextView.setText("Result: " + result);
        } else {
            resultTextView.setText("Please enter both numbers.");
        }
    }

    private enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}