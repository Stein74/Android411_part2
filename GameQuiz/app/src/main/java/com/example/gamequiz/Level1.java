package com.example.gamequiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Level1 extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView textView = findViewById(R.id.textView);
        textView.setText(R.string.level_1);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView imgLeft = findViewById(R.id.img_left);
        final ImageView imgRight = findViewById(R.id.img_right);

        //скругление
        imgLeft.setClipToOutline(true);//?? почему без стиля?
        imgRight.setClipToOutline(true);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide title
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// transparenet bacground
        dialog.setCancelable(false);//не закрывать кликом за пределами окна

        TextView btnClose = findViewById(R.id.button_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button btnContinue = findViewById(R.id.button_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Button btnBack = findViewById(R.id.button_back_level1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
            }
        });
    }
}