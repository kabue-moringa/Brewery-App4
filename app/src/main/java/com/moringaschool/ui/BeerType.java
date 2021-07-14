package com.moringaschool.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.adapters.BeerAdapter;
import com.moringaschool.brewer_app.CompanyActivity;
import com.moringaschool.brewer_app.CompanyActivity2;
import com.moringaschool.brewer_app.R;
import com.moringaschool.brewerydb.Constants;
import com.moringaschool.models.BreweriesResponse;
import com.moringaschool.network.BreweryApi;
import com.moringaschool.network.BreweryClient;
import com.moringaschool.ui.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  BeerType extends AppCompatActivity {
    @BindView(R.id.breweryrecyclerview)RecyclerView mRecyclerView;

    @BindView(R.id.errorTextView)TextView mErrorTextView;

    @BindView(R.id.progressBar)ProgressBar mProgressBar;

    private BeerAdapter mAdapter;
    private List<BreweriesResponse> BreweryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BreweryApi client = BreweryClient.getClient();
        Call<BreweriesResponse> call = client.getBreweriesResponse();

        call.enqueue(new Callback<BreweriesResponse>() {
            @Override
            public void onResponse(Call<BreweriesResponse>call, Response<BreweriesResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    BreweryType = response.body().getBreweriesResponse();
                    mAdapter = new BeerAdapter(BeerType.this, BreweryType);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(BeerType.this);
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
