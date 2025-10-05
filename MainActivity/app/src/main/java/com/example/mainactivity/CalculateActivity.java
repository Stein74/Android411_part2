package com.example.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.NumberFormat;
import java.util.Locale;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate2);

        Button calcButton = findViewById(R.id.calculate_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText costService = findViewById(R.id.cost_of_service);
                String tipStr = costService.getText().toString();
                int cost = Integer.parseInt(tipStr);
                double tip = 0;

                RadioGroup tipOptions = findViewById(R.id.tip_options);
                int currentId = tipOptions.getCheckedRadioButtonId();
                if (currentId == R.id.options_ten_persent){
                    tip = cost * 0.1;
                } else if (currentId == R.id.options_seven_persent){
                    tip = cost * 0.07;
                }else if (currentId == R.id.options_five_persent){
                    tip = cost * 0.05;
                }
                SwitchMaterial sm = findViewById(R.id.round_switch);
                if (sm.isChecked()){
                    tip = Math.ceil(tip);
                }

                TextView tipResult = findViewById(R.id.tip_result);
                Locale usLocale = new Locale("ru", "RU");
                NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(usLocale);
                String currecyTip = usCurrencyFormat.format(tip);

                tipResult.setText("Чаевые " + currecyTip);
            }
        });
    }
}