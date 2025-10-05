package com.example.discount1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editSum = findViewById(R.id.editSum);
        var btnCalc = findViewById(R.id.btnCalculate);
        TextView res = findViewById(R.id.discountResult);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double sum = Double.valueOf(editSum.getText().toString());
                double discountSum = 0;

                if (sum > 500){
                    discountSum = sum * 0.03;
                } else if (sum > 1000) {
                    discountSum = sum * 0.05;
                }
                
                res.setText(String.format("Сумма скидки: %.2f", discountSum));
            }
        });

    }
}