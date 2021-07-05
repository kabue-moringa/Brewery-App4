package com.moringaschool.brewer_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompanyActivity  extends AppCompatActivity {
    private String[] restaurants = new String[] {"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String company = intent.getStringExtra("company");
        textView=(TextView)findViewById(R.id.textView2) ;
        textView.setText("The leading Breweries");


//        String company = intent.getStringExtra("location");
    }
}
