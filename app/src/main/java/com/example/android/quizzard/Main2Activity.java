package com.example.android.quizzard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void proceedButton(View view){
        // intent being sent to the main activity when proceed button is clicked
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(view.getContext(), "Let's go!!!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
