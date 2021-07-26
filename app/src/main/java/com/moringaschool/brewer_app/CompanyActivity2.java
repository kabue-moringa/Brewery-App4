package com.moringaschool.brewer_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.adapters.BeerAdapter;
import com.moringaschool.brewerydb.Constants;
import com.moringaschool.models.BreweriesResponse;
import com.moringaschool.network.BreweryClient;
import com.moringaschool.ui.LoginActivity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyActivity2 extends AppCompatActivity {

//    sharedPrefernces.
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private static final String TAG = CompanyActivity2.class.getSimpleName();
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

        fetchPosts();
//        shared pren
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_COMPANY_KEY, null);
        if(mRecentAddress != null){
            fetchPosts(mRecentAddress);
        }

        Intent intent =getIntent();
        String name= intent.getStringExtra("name");
        String county =intent.getStringExtra("company");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String company) {
                addToSharedPreferences(company);
                fetchPosts(company);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(CompanyActivity2.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    private void fetchPosts(String company) {
        fetchPosts();
    }
    private void addToSharedPreferences(String country) {
        mEditor.putString(Constants.PREFERENCES_COMPANY_KEY,country).apply();
    }
    private void fetchPosts(){
        progressBar.setVisibility(View.VISIBLE);
        BreweryClient.getClient().getBreweriesResponse().enqueue(new Callback<List<BreweriesResponse>>() {
            @Override
            public void onResponse(Call<List<BreweriesResponse>> call, Response<List<BreweriesResponse>> response) {

                hideProgressBar();
                if (response.isSuccessful() && response.body() != null) {
                    TypeOfBreweries.addAll((Collection<? extends BreweriesResponse>) response.body());
                    mAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            private void hideProgressBar() {

            }

            @Override
            public void onFailure(Call<List<BreweriesResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CompanyActivity2.this,"Error "+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    }


