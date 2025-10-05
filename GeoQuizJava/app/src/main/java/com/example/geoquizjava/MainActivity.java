package com.example.geoquizjava;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;

    private final List<Question> questionBank = Stream.of(
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
        ).collect(Collectors.toList());

    private int currentIndex;

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

        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);
        Button nextButton = findViewById(R.id.next_button);
        Button prevButton = findViewById(R.id.prev_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(view -> checkAnswers(true));

        falseButton.setOnClickListener(view -> checkAnswers(false));

        nextButton.setOnClickListener(view -> nextQuestion());

        nextButton.setOnClickListener(view -> prevQuestion());

        prevButton.setOnClickListener(view -> prevQuestion());

        questionTextView.setOnClickListener(view -> nextQuestion());

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
        //currentIndex = (currentIndex - 1) % questionBank.size();
        currentIndex = (questionBank.size() + (currentIndex - 1)) % questionBank.size();
        updateQuestion();
    }

    private void checkAnswers(boolean userAnswer){
        boolean correctAnswer = questionBank.get(currentIndex).isAnswer();

        String MessageResId = userAnswer == correctAnswer ? "Верно!" : "Неверно!";

        Toast.makeText(this, MessageResId, LENGTH_SHORT).show();
    }
}