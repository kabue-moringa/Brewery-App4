package com.moringaschool.brewerydb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.BreweriesResponse;
import com.moringaschool.BreweryApi;
import com.moringaschool.BreweryClient;
import com.moringaschool.adapters.BeerAdapter;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.breweryrecyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private BeerAdapter mAdapter;
    public List<BreweriesResponse> mbrewery_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BreweryApi client = BreweryClient.getClient();
        Call<BreweriesResponse> call = client.getbreweries(Constants.BREWERYDB_BASE_URL);

        call.enqueue(new Callback<BreweriesResponse>() {

            @Override
            public void onResponse(Call<BreweriesResponse> call, Response<BreweriesResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    mbrewery_type = response.body().BreweriesResponse;
                    mAdapter = new BeerAdapter(MainActivity.this, mbrewery_type);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MainActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showBreweriesResponse();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<BreweriesResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showBreweriesResponse() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}