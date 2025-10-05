package com.example.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class registerActivity extends AppCompatActivity {

    EditText edit_name, edit_email;
    Button save;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edit_name = findViewById(R.id.edit_text_name);
        edit_email = findViewById(R.id.edit_text_email);
        save = findViewById(R.id.button_save);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);

        if (name != null){
            Intent intent = new Intent(registerActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, edit_name.getText().toString());
                editor.putString(KEY_EMAIL, edit_email.getText().toString());
                editor.apply();

                Intent intent = new Intent(registerActivity.this, HomeActivity.class);
                startActivity(intent);

                Toast.makeText(registerActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}