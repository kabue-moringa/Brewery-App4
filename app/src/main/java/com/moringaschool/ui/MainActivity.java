package com.moringaschool.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.brewer_app.CompanyActivity;
import com.moringaschool.brewer_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.findCompanyButton) Button mFindRestaurantsButton;;
    @BindView(R.id.textView)
    TextView mAppNameTextView;
    private Button mFindCompanyButton;
    EditText name;
    EditText company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindCompanyButton = (Button)findViewById(R.id.findCompanyButton);

        ButterKnife.bind(this);

    mFindCompanyButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
           startActivity(intent);


        }

    });
    }
}

