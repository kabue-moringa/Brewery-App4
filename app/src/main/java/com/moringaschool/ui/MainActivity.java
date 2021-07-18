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

import com.moringaschool.brewer_app.CompanyActivity;
import com.moringaschool.brewer_app.R;
import com.moringaschool.brewerydb.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
//    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.findCompanyButton) Button mFindCompanyButton;;
    @BindView(R.id.textView)
    TextView mAppNameTextView;
//    private Button mFindCompanyButton;
    EditText name;
    EditText company;
    EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindCompanyButton = (Button)findViewById(R.id.findCompanyButton);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

    mFindCompanyButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(v ==mFindCompanyButton) {
                String location = mLocationEditText.getText().toString();
                if(!(location).equals("")) {
                    addToSharedPreferences(location);
                }
                Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
                startActivity(intent);
            }

        }

        private void addToSharedPreferences(String location) {
            mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
        }

    });
    }

    @Override
    public void onClick(View v) {


    }
}

