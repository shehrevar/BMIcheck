package com.example.bmicheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private long backInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Splash Screen api call
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button next = findViewById(R.id.power);

        myBMI(); //method call
    }

    public void myBMI() {  // method declaration
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ConstraintLayout cl;
                cl = findViewById(R.id.clv);
                cl.setBackgroundResource(R.color.warm);
                final EditText heightText = findViewById(R.id.height);
                String heightStr = heightText.getText().toString();
                double height = Double.parseDouble(heightStr);
                double heightM = height / 100;
                final EditText weightText = findViewById(R.id.weight);
                String weightStr = weightText.getText().toString();
                double weight = Double.parseDouble(weightStr);
                double BMI = (weight) / (heightM * heightM);
                DecimalFormat df = new DecimalFormat("#.#");
                double BMI_trimmed = Double.parseDouble(df.format(BMI));
                final TextView BMIResult = findViewById(R.id.result);
                BMIResult.setText(Double.toString(BMI_trimmed));
                String BMI_Cat;
                if (BMI < 15) {
                    BMI_Cat = "Very severely underweight";
                } else if (BMI >= 15 && BMI < 16) {
                    BMI_Cat = "Severely underweight";
                } else if (BMI >= 16 && BMI < 18.5) {
                    BMI_Cat = "Underweight";
                } else if (BMI >= 18.5 && BMI < 25) {
                    BMI_Cat = "Normal";
                } else if (BMI >= 25 && BMI < 30) {
                    BMI_Cat = "Overweight";
                } else if (BMI >= 30 && BMI < 35) {
                    BMI_Cat = "Obese Class 1 - Moderately Obese";
                } else if (BMI >= 35 && BMI < 40) {
                    BMI_Cat = "Obese Class 2 - Severely Obese";
                } else {
                    BMI_Cat = "Obese Class 3 - Very Severely Obese";

                }
                final TextView BMICategory = findViewById(R.id.bmiCat);
                BMICategory.setText(BMI_Cat);

            }
        });
    }

    public void nextPage(View view) {
        Intent intent = new Intent(getApplicationContext(), PowerUp.class);
        startActivity(intent);
        Toast.makeText(this, "Redirecting", Toast.LENGTH_LONG)
                .show();
    }
//Tap twice to exit
    @Override
    public void onBackPressed() {
        if (backInterval + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();

                   } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backInterval = System.currentTimeMillis();
    }


}
