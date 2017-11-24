package com.example.radovan.geoquiz;

/**
 * Created by radovan on 11/12/17.
 */

class Question {
    private String mQuestionText;
    private boolean mAnswer;

    public Question(String question, boolean answer) {
        mAnswer = answer;
        mQuestionText = question;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(String questionText) {
        mQuestionText = questionText;
    }
}
