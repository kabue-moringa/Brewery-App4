package com.moringaschool.brewer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFindCompanyButton;
    EditText name;
    EditText company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindCompanyButton = (Button)findViewById(R.id.findCompanyButton);
        name=findViewById(R.id.editText);
        company=findViewById(R.id.login);


    mFindCompanyButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
           intent.putExtra("name",name.getText().toString());
          intent.putExtra("company",name.getText().toString());
           startActivity(intent);


        }

    });
    }
}

