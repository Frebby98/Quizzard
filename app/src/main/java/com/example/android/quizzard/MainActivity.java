package com.example.android.quizzard;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method is connected to the submit button as the name implies
    // this method acts as the power house, which affects other methods
    public void onSubmitClicked(View view) {
        String[] answers = evaluateGui();
        int result = evaluateQuiz(answers);
        toastResult(result);
    }

    //using ArrayList to set the options according the questions order
    public String[] evaluateGui() {
        boolean answerQuestion2 = false;
        boolean answerQuestion6 = false;
        boolean answerQuestion9 = false;

        //Number of questions, represented using ArrayList
        String[] ret = new String[10];

        //Below, views are being found for evaluation
        CheckBox checkBoxQuestion2Opt1 = (CheckBox) findViewById(R.id.great_divide);
        CheckBox checkBoxQuestion2Opt2 = (CheckBox) findViewById(R.id.hanging_garden);
        CheckBox checkBoxQuestion2Opt3 = (CheckBox) findViewById(R.id.light_house);
        CheckBox checkBoxQuestion2Opt4 = (CheckBox) findViewById(R.id.colossus);

        CheckBox checkBoxQuestion6Opt1 = (CheckBox) findViewById(R.id.radio_nigeria);
        CheckBox checkBoxQuestion6Opt2 = (CheckBox) findViewById(R.id.radio_asia);
        CheckBox checkBoxQuestion6Opt3 = (CheckBox) findViewById(R.id.radio_north);
        CheckBox checkBoxQuestion6Opt4 = (CheckBox) findViewById(R.id.radio_europe);

        EditText editTextQuestion7 = (EditText) findViewById(R.id.edit_off);
        EditText editTextQuestion8 = (EditText) findViewById(R.id.edit_war);

        CheckBox checkBoxQuestion9Opt1 = (CheckBox) findViewById(R.id.radio_blue);
        CheckBox checkBoxQuestion9Opt2 = (CheckBox) findViewById(R.id.radio_green);
        CheckBox checkBoxQuestion9Opt3 = (CheckBox) findViewById(R.id.radio_red);
        CheckBox checkBoxQuestion9Opt4 = (CheckBox) findViewById(R.id.radio_yellow);

        EditText editTextQuestion10 = (EditText) findViewById(R.id.edit_sport);

        //Determining, the right answers from wrong
        if (!checkBoxQuestion2Opt1.isChecked() &&
                checkBoxQuestion2Opt2.isChecked() &&
                checkBoxQuestion2Opt3.isChecked() &&
                checkBoxQuestion2Opt4.isChecked()) {
            answerQuestion2 = true;
        }
        if (!checkBoxQuestion6Opt1.isChecked() &&
                checkBoxQuestion6Opt2.isChecked() &&
                checkBoxQuestion6Opt3.isChecked() &&
                checkBoxQuestion6Opt4.isChecked()) {
            answerQuestion6 = true;
        }
        if (checkBoxQuestion9Opt1.isChecked() &&
                checkBoxQuestion9Opt2.isChecked() &&
                checkBoxQuestion9Opt3.isChecked() &&
                !checkBoxQuestion9Opt4.isChecked()) {
            answerQuestion9 = true;
        }

        ret[0] = evaluateRadioGroup(R.id.radio_group_question_1).toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = evaluateRadioGroup(R.id.radio_group_question_4).toLowerCase();
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();
        ret[5] = Boolean.toString(answerQuestion6);
        ret[6] = editTextQuestion7.getText().toString().toLowerCase();
        ret[7] = editTextQuestion8.getText().toString().toLowerCase();
        ret[8] = Boolean.toString(answerQuestion9);
        ret[9] = editTextQuestion10.getText().toString().toLowerCase();

        return ret;

    }

    // method handles radio groups by getting it's view and evaluating
    // retuens a string
    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = (RadioGroup) findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();

        radioButtonQuestion = (RadioButton) findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }
        return (String) radioButtonQuestion.getText();
    }

    // All correct answers to questions are initialized to an array list
    // For loop orderly evaluates each answer accordingly
    // Returns result for usage
    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"1914", "true", "54", "moon", "the burj khalifa",
                "true", "offside", "13", "true", "court"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }
        return result;
    }

    // Displaying the toast result of a player
    // Depending on the accuracy of the player, he is graded accordingly
    public void toastResult(int attempts) {
        //finding the players name view
        EditText nameInput = (EditText) findViewById(R.id.edit_name);

        // getting the name, converting edit to string
        String name = nameInput.getText().toString();

        String message = name + ", you got " + attempts +
                " questions right out of 10. And you scored ";

        if (attempts == 0) {
            message += "0% out of 100%. Are you sure you selected any option.";
        } else if (attempts == 1) {
            message += " 10% out of 100%. Poor luck.";
        } else if (attempts == 2) {
            message += "20% out of 100%. You could do better.";
        } else if (attempts == 3) {
            message += "30% out of 100%. Quite nice.";
        } else if (attempts == 4) {
            message += "40% out of 100%. Really nice.";
        } else if (attempts == 5) {
            message += "50% out of 100%. Great!";
        } else if (attempts == 6) {
            message += "60% out of 100%. Absolutely awesome!";
        } else if (attempts == 7) {
            message += "70% out of 100%. Absolutely getting there!";
        } else if (attempts == 8) {
            message += "80% out of 100%. Almost!! Nice Job.";
        } else if (attempts == 9) {
            message += "90% out of 100%. Mehn, That was close!!!";
        } else if (attempts == 10) {
            message += "100% out of 100%. Mehn, Who are you?!!!";
        }

        //length of the toast message, determining the persons score
        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG);

        reportResult.show();
    }

    // Method handles resetting all questions and options
    public void onResetButtonClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_name);
        editText.setText("");

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_question_1);
        radioGroup.clearCheck();

        CheckBox checkBox = (CheckBox) findViewById(R.id.great_divide);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.hanging_garden);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.light_house);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.colossus);
        checkBox.setChecked(false);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_question_4);
        radioGroup.clearCheck();

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();

        checkBox = (CheckBox) findViewById(R.id.radio_nigeria);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.radio_asia);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.radio_north);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.radio_europe);
        checkBox.setChecked(false);

        editText = (EditText) findViewById(R.id.edit_off);
        editText.setText("");

        editText = (EditText) findViewById(R.id.edit_war);
        editText.setText("");

        checkBox = (CheckBox) findViewById(R.id.radio_blue);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.radio_green);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.radio_red);
        checkBox.setChecked(false);

        checkBox = (CheckBox) findViewById(R.id.radio_yellow);
        checkBox.setChecked(false);
        editText = (EditText) findViewById(R.id.edit_sport);
        editText.setText("");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    // Handles the displaying of q7 hint when the hint button is clicked
    public void q7HintButton(View view) {

        Toast hintToast = Toast.makeText(getApplicationContext(),
                "Occurs in the game of soccer",
                Toast.LENGTH_SHORT);

        hintToast.show();

    }

    // Handles the displaying of q8 hint when the hint button is clicked
    public void q8HintButton(View view) {


        Toast hintToast = Toast.makeText(getApplicationContext(),
                "Remove 19 from the year before the first world war began",
                Toast.LENGTH_SHORT);

        hintToast.show();

    }

    // Handles the displaying of q10 hint when the hint button is clicked
    public void q10HintButton(View view) {

        Toast hintToast = Toast.makeText(getApplicationContext(),
                "Also a place we hope to get justice.", Toast.LENGTH_LONG);
        hintToast.show();

    }
}