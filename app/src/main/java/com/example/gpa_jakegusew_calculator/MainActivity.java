package com.example.gpa_jakegusew_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  implements TextWatcher{ //Implements textWatcher so that we can use OnTextChanged
                                                                                // to change button "Clear Form" to "Calculate GPA"

    ConstraintLayout layout1; //Declare layout for changing background color
    EditText editText_gpa1, editText_gpa2,
            editText_gpa3, editText_gpa4, editText_gpa5; //EditTexts
    TextView textView_output; //Output box
    Button button_calculate;//Calculate button
    float gpa1, gpa2, gpa3, gpa4, gpa5, result; //Floats for Calculation
    static boolean buttonPressed; //Static bool used to store state of button, so its functionality can be changed between clear / calculate

    public boolean checkEmpty(EditText editText) { //Simple method to check if an EditText is empty
        return editText.getText().toString().length() == 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = (ConstraintLayout) findViewById(R.id.main_activity_layout_portrait); //Declare layout

        editText_gpa1 = (EditText) findViewById(R.id.editText_course1); //Declare EditTexts, add listeners to detect when edittexts are changed
        editText_gpa1.addTextChangedListener(this);
        editText_gpa2 = (EditText) findViewById(R.id.editText_course2);
        editText_gpa2.addTextChangedListener(this);
        editText_gpa3 = (EditText) findViewById(R.id.editText_course3);
        editText_gpa3.addTextChangedListener(this);
        editText_gpa4 = (EditText) findViewById(R.id.editText_course4);
        editText_gpa4.addTextChangedListener(this);
        editText_gpa5 = (EditText) findViewById(R.id.editText_course5);
        editText_gpa5.addTextChangedListener(this);
        textView_output = (TextView) findViewById(R.id.textView_result);
        button_calculate = (Button) findViewById(R.id.button_calculate);


        button_calculate.setOnClickListener(new View.OnClickListener() { //Onclick listener for calculate button
            public void onClick(View v) {
                if (checkEmpty(editText_gpa1)){ //Check if the fields are empty when pressed
                    editText_gpa1.setError("This field cannot be empty"); //Also sets an error on the EditText
                    return; //Return so that program doesn't crash trying to compute with empty value
                }
                if (checkEmpty(editText_gpa2)){
                    editText_gpa2.setError("This field cannot be empty");
                    return;
                }
                if (checkEmpty(editText_gpa3)){
                    editText_gpa3.setError("This field cannot be empty");
                    return;
                }
                if (checkEmpty(editText_gpa4)){
                    editText_gpa4.setError("This field cannot be empty");
                    return;
                }
                if (checkEmpty(editText_gpa5)){
                    editText_gpa5.setError("This field cannot be empty");
                    return;
                }

                if (!(buttonPressed)){ //If buttonPressed is false then it is going to calculate the GPA
                    gpa1 = Integer.parseInt(editText_gpa1.getText().toString());
                    gpa2 = Integer.parseInt(editText_gpa2.getText().toString());
                    gpa3 = Integer.parseInt(editText_gpa3.getText().toString());
                    gpa4 = Integer.parseInt(editText_gpa4.getText().toString());
                    gpa5 = Integer.parseInt(editText_gpa5.getText().toString());
                    result = (gpa1 + gpa2 + gpa3 + gpa4 + gpa5) / 5;

                    textView_output.setText(String.valueOf(result)); //Set the output
                    if (result < 60){
                        layout1.setBackgroundColor(Color.RED); //Change color based on grade
                    }
                    else if (result < 80 && result >= 60){
                        layout1.setBackgroundColor((Color.YELLOW));
                    }
                    else if (result >= 80) {
                        layout1.setBackgroundColor((Color.GREEN));
                    }
                    buttonPressed = true; //Set buttonpressed true so next time onclick is called the screen will clear instead of calculate
                    button_calculate.setText(R.string.calculateButton_clear);
                    }
                else if (buttonPressed)   { //If buttonPressed is true then clear the form
                    editText_gpa1.setText("");
                    editText_gpa2.setText("");
                    editText_gpa3.setText(""); //Clear EditTexts
                    editText_gpa4.setText("");
                    editText_gpa5.setText("");
                    buttonPressed = false; //Set buttonPressed to false so next time the calculation code is executed
                    button_calculate.setText(R.string.calculateButton_calculate);
                    textView_output.setText(""); // Reset buttons and textViews
                    layout1.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        button_calculate = (Button) findViewById(R.id.button_calculate);
        button_calculate.setText(R.string.calculateButton_calculate);
        buttonPressed = false; //Set buttonPressed to false so that it will execute the calculation code when clicked
    }

    public void afterTextChanged(Editable editable) {
    }
}

