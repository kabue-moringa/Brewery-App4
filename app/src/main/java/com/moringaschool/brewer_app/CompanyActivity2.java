package com.moringaschool.brewer_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.adapters.BeerAdapter;
import com.moringaschool.brewerydb.Constants;
import com.moringaschool.models.BreweriesResponse;
import com.moringaschool.network.BreweryApi;
import com.moringaschool.network.BreweryClient;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyActivity2 extends AppCompatActivity {

//    sharedPrefernces.
//    private SharedPreferences mSharedPreferences;
//    private String mRecentAddress;
    TextView textView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    private BeerAdapter mAdapter;
    public List<BreweriesResponse> TypeOfBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company2);
        recyclerView = findViewById(R.id.idrecycler);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new BeerAdapter(TypeOfBreweries);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
//        shared preference
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        Log.d("Shared Pref Location", mRecentAddress); changing it to String location.
//        String location = mRecentAddress;
        Intent intent = new Intent();
        String location = intent.getStringExtra("location");

        fetchPosts();
    }

    private void fetchPosts() {
        progressBar.setVisibility(View.VISIBLE);
        BreweryClient.getClient().getBreweriesResponse().enqueue(new Callback<List<BreweriesResponse>>() {
            @Override
            public void onResponse(Call<List<BreweriesResponse>> call, Response<List<BreweriesResponse>> response) {


                if (response.isSuccessful() && response.body() != null) {
                    TypeOfBreweries.addAll((Collection<? extends BreweriesResponse>) response.body());
                    mAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<BreweriesResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CompanyActivity2.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
