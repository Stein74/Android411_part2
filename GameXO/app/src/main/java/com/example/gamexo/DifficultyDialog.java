package com.example.gamexo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DificultyDialog extends DialogFragment {

    private RadioButton buttonEasy, buttonHard;
    private Button buttonOk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_difficulty, container, false);

        buttonEasy = view.findViewById(R.id.easy_button);
        buttonHard = view.findViewById(R.id.hard_button);

        if (MainActivity.fieldSize == 3){
            buttonEasy.setChecked(true);
        }else{
            buttonHard.setChecked(true);
        }

        buttonOk = view.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fieldSize = buttonEasy.isChecked() ? 3 : 5;

                dismiss();
            }
        });

        return view;
    }
}
