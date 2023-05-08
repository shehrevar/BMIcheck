package com.example.bmicheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class PowerUp extends AppCompatActivity {
    Button web;
    ImageButton btnCall;
    ImageView btnEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_up);
        web =   findViewById(R.id.imageButton);
        btnCall =   findViewById(R.id.imageCall);
        btnEmail = findViewById(R.id.imageEmail);
    //Parent Layout
        View pLayout = findViewById(android.R.id.content);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PowerUp.this,
                        "Redirecting to external link",
                        Toast.LENGTH_SHORT).show();
                String url = "https://rku.ac.in";
                // Creating an explicit intent to open the
                // link stored in variable url
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callI = new Intent(Intent.ACTION_DIAL);
                callI.setData(Uri.parse("tel:9673651546"));
                startActivity(callI);
            }
        });
    btnEmail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent emailI = new Intent(Intent.ACTION_SENDTO);
            emailI.setType("message/rfc822");
            // IF setType and setData to be used in same code better to use setDataAndType() to avoid nullification
            emailI.setData(Uri.
              parse("mailto:shehrevar.davierwala@rku.ac.in"));
           // emailI.setDataAndType(Uri.parse("mailto:shehrevar.davierwala@rku.ac.in"),"message/rfc822");
            startActivity(Intent.createChooser(emailI, "Choose an Email client :"));
        }
    });
        // Toast Attached to Parent Layout with action button
        Snackbar.make(pLayout, "This is Snackbar", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }) //(getResources().getColor(android.R.color.holo_red_light )) - decrepted
                .setActionTextColor(ContextCompat.getColor(getBaseContext(),android.R.color.holo_red_light ))
                .show();

        //move to other activity
        Button nextBtn;
        nextBtn=findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(PowerUp.this,allWell.class);
                startActivity(next);
            }
        });

    }


}