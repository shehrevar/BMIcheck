package com.example.bmicheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;

public class allWell extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    SwipeFlingAdapterView flingAdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_well);
        flingAdapterView=findViewById(R.id.box);
        //call quotes from string.xml
        String [] quote = getResources().getStringArray(R.array.Squotes);
        //Squote is String array in String.xml
        //quote is variable that fetches text from String.xml
        arrayList=new ArrayList<>(Arrays.asList(quote));
        //arrayList will fetch each quote one by one
        arrayAdapter=new ArrayAdapter<>(this,R.layout.quotes, R.id.Qtext,arrayList);
        //the quote fetch by arrayList will be displayed in Layout Quotes with textView named Qtext
        flingAdapterView.setAdapter(arrayAdapter);
        //flingAdapter will now display the text and provide swipe effects
        flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                arrayList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Toast.makeText(allWell.this, "Swiped left", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(allWell.this, "Swiped Right", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });
        flingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int i, Object o) {
                //i stores index of quote that is clicked
                Intent move = new Intent(allWell.this, nextAct.class);
                String item=arrayList.get(i); //the index of clicked quote is submitted to arrayList so that text can be passed to other activity
                move.putExtra("quote",item);
                startActivity(move);

            }
        });
    }
}