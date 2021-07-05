package com.moringaschool.brewer_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompanyActivity  extends AppCompatActivity {
    private ListView mListView;
    private String[] companies = new String[] {"Anheuser-Busch InBev.", "Tsingtao Brewery Group.",
            "Carlsberg", "Groupe Castel", "Molson Coors Brewing", "Heineken",
            "Yanjing", " China Resources Snow Breweries", " Asahi", "Tsingtao Brewery Group",
    };

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, companies);
        mListView.setAdapter(adapter);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String company = intent.getStringExtra("company");
        textView=(TextView)findViewById(R.id.textView2) ;
        textView.setText("Largest Brewies");


//        String company = intent.getStringExtra("location");
    }
}
