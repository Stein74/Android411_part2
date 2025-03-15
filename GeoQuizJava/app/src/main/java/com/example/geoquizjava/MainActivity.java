package com.example.geoquizjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button prevButton;
    private TextView questionTextView;

    private ArrayList<Question> questionBank;

    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        questionBank.add(new Question(R.string.question_australia, true));
        questionBank.add(new Question(R.string.question_oceans, true));
        questionBank.add(new Question(R.string.question_mideast, true));
        questionBank.add(new Question(R.string.question_africa, true));
        questionBank.add(new Question(R.string.question_americas, true));
        questionBank.add(new Question(R.string.question_asia, true));

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevQuestion();
            }
        });

        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion(){
        int questionTextResId = questionBank.get(currentIndex).getTextResId();
        questionTextView.setText(questionTextResId);
    }

    private void nextQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size();
        updateQuestion();
    }

    private void prevQuestion(){
        currentIndex = (currentIndex - 1) % questionBank.size();
        updateQuestion();
    }

    private void checkAnswers(boolean userAnswer){
        boolean correctAnswer = questionBank.get(currentIndex).isAnswer();

        String MessageResId = userAnswer == correctAnswer ? "Верно!" : "Неверно!";

        Toast.makeText(this, MessageResId, Toast.LENGTH_SHORT).show();
    }
}