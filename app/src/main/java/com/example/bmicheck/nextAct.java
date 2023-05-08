package com.example.bmicheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class nextAct extends AppCompatActivity {
    private TextView txt;
    private Button Sbtn,Cbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        txt=findViewById(R.id.Qtext2);
        Sbtn=findViewById(R.id.sharebtn);
        Cbtn=findViewById(R.id.copybtn);
        String rec= getIntent().getStringExtra("quote");
        txt.setText(rec);
        Sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,rec);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share using");
                startActivity(shareIntent);
            }
        });
        Cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip=
                        ClipData.newPlainText("Copying",txt.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(nextAct.this,"Copied", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}