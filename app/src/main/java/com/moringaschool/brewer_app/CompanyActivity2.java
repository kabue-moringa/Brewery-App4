package com.moringaschool.brewer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyActivity2 extends AppCompatActivity {
    TextView textView;
    ListView listView;

    private String[] company = new String[] {"Cloudwater Brew Co.", " Founders Brewing Company (Michigan)",
            "Firestone Walker Brewing (California)", " AleSmith Brewing Company (California) ", "Molson Coors Brewing", "Cigar City Brewing (Florida) ",
            "Sante Adairius Rustic Ales ", "Tree House Brewing Company", "Trillium Brewing Company ", "Hill Farmstead Brewery",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company2);
        listView = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textView3);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, company);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String company = ((TextView)view).getText().toString();
                Toast.makeText(CompanyActivity2.this,company,Toast.LENGTH_LONG).show();

            }
        });
        textView.setText("The best Breweries");
    }
}