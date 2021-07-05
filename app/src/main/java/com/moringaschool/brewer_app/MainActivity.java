package com.moringaschool.brewer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindCompanyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindCompanyButton = (Button)findViewById(R.id.findCompanyButton);


    mFindCompanyButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();
        }
    });
    }
}

