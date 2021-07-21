package com.moringaschool.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.adapters.FirebaseCompanyViewHolder;
import com.moringaschool.brewer_app.R;
import com.moringaschool.brewerydb.Constants;
import com.moringaschool.models.BreweriesResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCompanyListActivity extends AppCompatActivity {
    private DatabaseReference mCompanyReference;
    private FirebaseRecyclerAdapter<BreweriesResponse, FirebaseCompanyViewHolder>mFirebaseAdapter;

    @BindView(R.id.breweryrecyclerview) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)  ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mCompanyReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_COMPANY);
        setUpFirebaseAdapter();
        hideProgressBar();
        showCompanys();
    }
    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<BreweriesResponse> options =
                new FirebaseRecyclerOptions.Builder<BreweriesResponse>()
                        .setQuery(mCompanyReference, BreweriesResponse.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<BreweriesResponse, FirebaseCompanyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseCompanyViewHolder holder, int position, @NonNull BreweriesResponse model) {
                FirebaseCompanyViewHolder.bindBreweriesResponse(model);

            }

            @NonNull
            @Override
            public FirebaseCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_type, parent, false);
                return new FirebaseCompanyViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
    private void showCompanys() {

        mRecyclerView.setVisibility(View.VISIBLE);
    }


    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}