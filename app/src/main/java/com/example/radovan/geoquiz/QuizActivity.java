package com.example.radovan.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mtrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;

    private TextView mQuestionTextView;
    private Question[] mQuestionList = null;

    private int mCurrentQuestionIndex = 0;

    private static final String QUESTION_INDEX = "QUESTION_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentQuestionIndex = savedInstanceState.getInt(QUESTION_INDEX);
        }

        mtrueButton = (Button) findViewById(R.id.true_button);

        mtrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQuestionIndex = (mCurrentQuestionIndex + 1) % mQuestionList.length;
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mCurrentQuestionIndex == 0) {
                    mCurrentQuestionIndex = mQuestionList.length - 1;
                } else {
                    mCurrentQuestionIndex--;
                }

                updateQuestion();
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQuestionIndex = (mCurrentQuestionIndex + 1) % mQuestionList.length;
                updateQuestion();
            }
        });

        createQuestions();
        updateQuestion();
    }

    private void updateQuestion() {
        Question question = mQuestionList[mCurrentQuestionIndex];
        mQuestionTextView.setText(question.getQuestionText());
    }

    private void createQuestions() {
        mQuestionList = new Question[] {
                new Question("Is Belgrade the capital of Serbia?", true),
                new Question("Are bricks green?", false)
        };
    }

    private void checkAnswer(boolean answer) {
        Question question = mQuestionList[mCurrentQuestionIndex];
        String message;
        if (answer == question.isAnswer()) {
            message = getString(R.string.correct_toast);
        } else {
            message = getString(R.string.incorrect_toast);
        }
        Toast.makeText(QuizActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(QUESTION_INDEX, mCurrentQuestionIndex);
    }
}
