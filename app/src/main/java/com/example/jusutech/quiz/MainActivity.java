package com.example.jusutech.quiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Button buttonTrue;
    Button buttonFalse;
    TextView textView;
    TextView scoreLevel;
    int mIndex = 0;
    int score = 0;
    ProgressBar progressBar;


    /*here we create the questions object with an array so that we can pass our values to the constructor. the question
    and the answer to it */
    /*Hence we pass the array of questions to our class*/
    Question[] myQuestions = new Question[]{
            new Question(R.string.question_1,false),
            new Question(R.string.question_2,false),
            new Question(R.string.question_3,true),
            new Question(R.string.question_4,true),
            new Question(R.string.question_5,true),
            new Question(R.string.question_6,false),
            new Question(R.string.question_7,false),
            new Question(R.string.question_8,true),
            new Question(R.string.question_9,true),
            new Question(R.string.question_10,false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //here we retrieve the values that were saved on app pause
        if(savedInstanceState != null){
            score = savedInstanceState.getInt("Score");
            mIndex = savedInstanceState.getInt("index");
        }else
        {
            mIndex = 0;
            score = 0;
        }
        setContentView(R.layout.activity_main);

        buttonTrue = (Button)findViewById(R.id.trueText);
        buttonFalse = (Button)findViewById(R.id.falseText);
        textView = (TextView)findViewById(R.id.question);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        scoreLevel = (TextView)findViewById(R.id.scoreLevel);


        textView.setText(myQuestions[mIndex].getmQuestionNumber());
        scoreLevel.setText("Score " + score + "/" + myQuestions.length);


        //set click listeners on the two buttons on the screen
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    //this gets 100 divided by the number of questions, rounds it off so that it can well track the progress bar
    final int PROGRESS_BAR = (int)Math.ceil(100.0 / myQuestions.length);

    //this method is called when the user presses one of the buttons
    public void updateQuestion(){

        //this will make the index go back to 0 if the index exceeds the number of questions
        //hence thats how we know the questions are done and display a dialog
        //otherwise we keep moving to the next questions

        mIndex = (mIndex+1)%myQuestions.length;
        if(mIndex == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //title of the dialog
            builder.setTitle("End of Quiz");
            builder.setMessage("You have scored "+ score+ " Points");
            //this makes it fail to be canceled
            builder.setCancelable(false);
            builder.setPositiveButton("End Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.show();
        }
        
        textView.setText(myQuestions[mIndex].getmQuestionNumber());
        progressBar.incrementProgressBy(PROGRESS_BAR);
        scoreLevel.setText("Score " + score + "/" + myQuestions.length);
    }


    //here we need to know which answer was selected by the user
    public void checkAnswer(boolean answerSelected){
        //we check if it corresponds with the right answer by using the object to call it from the class it is in
        boolean correctAnswer = myQuestions[mIndex].ismQuestionAnswer();

        if(answerSelected == correctAnswer){
            //increment the score if the user gets it right
            score= score + 1;
            Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
            //score+=1;
        }
        else {
            //dont increment the score if the answer is incorrect
            Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    //here we need to save the instance of the app so that it can stay with the values if it is paused
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //values are stored in a key value pair
        outState.putInt("Score",score);
        outState.putInt("index",mIndex);
    }
}
