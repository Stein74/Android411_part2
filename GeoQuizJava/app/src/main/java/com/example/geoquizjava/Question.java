package com.example.geoquizjava;

import androidx.annotation.StringRes;

public class Question {

    private int textResId;
    private boolean answer;

    public Question(@StringRes int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResIs(int textResIs) {
        this.textResId = textResIs;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
