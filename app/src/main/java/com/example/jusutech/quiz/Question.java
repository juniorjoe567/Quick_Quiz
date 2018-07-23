package com.example.jusutech.quiz;

/**
 * Created by Jusutech on 7/19/2018.
 */

public class Question {
    int mQuestionNumber;
    boolean mQuestionAnswer;

    public Question(int QuestionNumber, boolean QuestionAnswer){
        mQuestionAnswer = QuestionAnswer;
        mQuestionNumber = QuestionNumber;
    }

    public int getmQuestionNumber() {
        return mQuestionNumber;
    }

    public void setmQuestionNumber(int mQuestionNumber) {
        this.mQuestionNumber = mQuestionNumber;
    }

    public boolean ismQuestionAnswer() {
        return mQuestionAnswer;
    }

    public void setmQuestionAnswer(boolean mQuestionAnswer) {
        this.mQuestionAnswer = mQuestionAnswer;
    }
}
