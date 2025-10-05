package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bPlus = findViewById(R.id.buttonPlus);
        Button bMinus = findViewById(R.id.buttonMinus);
        Button bMul = findViewById(R.id.buttonMultiply);
        Button bDiv = findViewById(R.id.buttonDivide);

        num1 = findViewById(R.id.Number1);
        num2 = findViewById(R.id.Number2);

        result = findViewById(R.id.result);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] numbers = getNumbers();
                String action = ((Button) view).getText().toString();
                double resultVal = 0;

                switch (action){
                    case "+":
                        resultVal = numbers[0] + numbers[1];
                        break;
                    case "-":
                        resultVal = numbers[0] - numbers[1];
                        break;
                    case "*":
                        resultVal = numbers[0] * numbers[1];
                        break;
                    case "/":
                        resultVal = numbers[0] / numbers[1];
                        break;
                }

                String restext = String.format("Result: %1$.1f %3$s %2$.1f = %4$.1f",
                        numbers[0], numbers[1], action, resultVal);

                result.setText(restext);
                result.setVisibility(View.VISIBLE);
            }
        };

        bPlus.setOnClickListener(listener);
        bMinus.setOnClickListener(listener);
        bMul.setOnClickListener(listener);
        bDiv.setOnClickListener(listener);
    }

    private double[] getNumbers(){
        String t = num1.getText().toString();
        double n1 = Double.valueOf(t.isEmpty() ? "0" : t);
        t = num2.getText().toString();
        double n2 = Double.valueOf(t.isEmpty() ? "0" : t);

        double[] res = {n1, n2};
        return res;
    }
}