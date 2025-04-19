package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    private TextView sampleTextView;
/*    private int currentProgress = 0;
    private ProgressBar progressBar;

    private EditText editTextKeyword;
    private ChipGroup chipGroup;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 /*       int green = Color.parseColor("#009212");
        int orange = Color.parseColor("#FF6F00");

        RadioGroup radioGroup =findViewById(R.id.radioGroup);
        TextView textView = findViewById(R.id.textColor);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton){
                    textView.setTextColor(green);
                }else if (checkedId == R.id.radioButton2){
                    textView.setTextColor(orange);
                }
            }
        });

        Button button = findViewById(R.id.changeColor);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()){
                    button.setBackgroundColor(green);
                }else {
                    button.setBackgroundColor(orange);
                }
            }
        });*/

/*        Switch sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sw.setTextColor(orange);
                }else {
                    sw.setTextColor(green);
                }
            }
        });

        ConstraintLayout constraintLayout = findViewById(R.id.main);
        SwitchMaterial switchMaterial = findViewById(R.id.switchMaterial);
        switchMaterial.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (switchMaterial.isChecked()){
                    switchMaterial.setThumbDrawable(getDrawable(R.drawable.baseline_access_time_filled_24));
                    switchMaterial.setText("turn on night mode");
                    switchMaterial.setThumbTintList(ColorStateList.valueOf(Color.BLUE));
                    switchMaterial.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#004CFF")));
                    constraintLayout.setBackgroundResource(R.color.bg);
                }else {
                    switchMaterial.setThumbDrawable(getDrawable(R.drawable.sunny));
                    switchMaterial.setText("turn on light mode");
                    switchMaterial.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#FF6F00")));
                    constraintLayout.setBackgroundResource(R.color.yellow);
                }
            }
        });*/

        sampleTextView = findViewById(R.id.sampleTextView);
        CheckBox boldCheckBox = findViewById(R.id.boldCheckBox);
        CheckBox italicCheckBox = findViewById(R.id.italicCheckBox2);

        boldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTestStyle(isChecked, italicCheckBox.isChecked());
            }
        });

        italicCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTestStyle(boldCheckBox.isChecked(), isChecked);
            }
        });

/*        Button buttonForProgress = findViewById(R.id.buttonForProgress);
        buttonForProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar pb = findViewById(R.id.progressBar);
                pb.setVisibility(View.VISIBLE);
            }
        });*/

       /* progressBar = findViewById(R.id.progressBarHor);
        Button startProgress = findViewById(R.id.startProgress);
        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentProgress += 10;
                progressBar.setProgress(currentProgress);
                progressBar.setMax(100);
            }
        });*/

/*        Chip chip1 = findViewById(R.id.chip);
        chip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Action complited", Toast.LENGTH_SHORT).show();
            }
        });*/

/*        editTextKeyword = findViewById(R.id.editTextKeyword);
        chipGroup = findViewById(R.id.chipGroup);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonShow = findViewById(R.id.buttonShow);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewChip();
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSeletions();
            }
        });*/
        FloatingActionButton actionButton = findViewById(R.id.addNote);
        EditText noteText = findViewById(R.id.noteText);
        TextView notes = findViewById(R.id.notes);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote(noteText, notes);
            }
        });
    }

    public void addNote(EditText noteText, TextView notes){

        String note = noteText.getText().toString();
        if (note.isBlank()){
            Snackbar.make(noteText, "enter note", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            return;
        }
        notes.append(note + "\n");
        noteText.setText("");
    }
/*    public void addNewChip(){
        String keyword = editTextKeyword.getText().toString();
        if (keyword.isBlank()){
            Toast.makeText(this, "Enter the keyword",Toast.LENGTH_SHORT).show();
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        Chip newChip = (Chip) inflater.inflate(R.layout.layout_chip_entry, chipGroup, false);
        newChip.setText(keyword);

        chipGroup.addView(newChip);
        editTextKeyword.setText("");

        newChip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChipCloseIconClicked((Chip) v);
            }
        });
    }

    private void showSeletions(){
        int count = chipGroup.getChildCount();
        String s = "";
        for (int i = 0; i < count; i++) {
            Chip child = (Chip) chipGroup.getChildAt(i);
            if (!child.isChecked()){
                continue;
            }

            s += ((s.isBlank() ? "" : ", ") + child.getText().toString());
        }
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void handleChipCloseIconClicked(Chip chip){
        chipGroup.removeView(chip);
    }*/

    public void changeTestStyle(boolean bold, boolean italic){
        if (bold && italic){
            sampleTextView.setTypeface(null, Typeface.BOLD_ITALIC);
        }else if (bold){
            sampleTextView.setTypeface(null, Typeface.BOLD);
        }else if (italic){
            sampleTextView.setTypeface(null, Typeface.ITALIC);
        }else {
            sampleTextView.setTypeface(null, Typeface.NORMAL);
        }
    }
}