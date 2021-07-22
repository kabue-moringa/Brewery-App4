package com.moringaschool.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.brewer_app.CompanyActivity;
import com.moringaschool.brewer_app.R;
import com.moringaschool.brewerydb.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private DatabaseReference mSearchedCompanyReference;
    @BindView(R.id.savedCompanyButton) Button mSavedCompanyButton;
    @BindView(R.id.findCompanyButton) Button mFindCompanyButton;;
//    @BindView(R.id.textView)
    EditText name;
    EditText company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindCompanyButton = (Button)findViewById(R.id.findCompanyButton);
//        ButterKnife.bind(this);

//;  mFindCompanyButton.setOnClickListener(this);
 mSavedCompanyButton.setOnClickListener(this);
    mFindCompanyButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(v ==mFindCompanyButton) {
                Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
                startActivity(intent);
            }
if (v ==mSavedCompanyButton){
    Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
    startActivity(intent);
}
        }
//        public void saveCompanyToFirebase(String company){
//            mSearchedCompanyReference.push().setValue(company);
        });

    }

    @Override
    public void onClick(View v) {


    }
}

